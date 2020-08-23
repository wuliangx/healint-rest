package services;

import java.io.FileReader;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.typesafe.config.Config;

import controllers.post.PostRepository;
import models.Order;
import models.OrderCodec;

public class StartupService {
	private static final String CSV_PATH = "app.loadcsv";
	private static final Logger log = LoggerFactory.getLogger(StartupService.class);
	
	@Inject
	public StartupService(Config config, PostRepository repository, OrderCodec codec) {
		if (config.hasPath(CSV_PATH)) {
			String path = config.getString(CSV_PATH);
			try {
				loadCSV(path, repository, codec);
			} catch (Exception exp) {
				log.error("Exception loading csv ", exp);
			}
		}
	}
	
	public void loadCSV(String path, PostRepository repository, OrderCodec codec) throws Exception {
		CompletableFuture<Order> orderProcess = CompletableFuture.completedFuture(null);
				
		boolean readFirstLine = false;
		
		CSVReader reader = new CSVReader(new FileReader(path));
		Iterator<String[]> it = reader.iterator();
		
		try {
			while (it.hasNext()) {
				String[] parts = it.next();
				if (readFirstLine) {
					Order currOrder = codec.getOrder(parts);
					orderProcess = orderProcess.thenCompose(dummy -> repository.create(currOrder))
							.exceptionally(exp -> {
								log.error("Exception processing {}: {}", parts, exp);
								return null;
							});
				} else {
					readFirstLine = true;
				}
			}
			
			orderProcess.get();
		} catch (Exception exp) {
			throw exp;
		} finally {
			reader.close();
		}
	}
}

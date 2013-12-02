package de.goeuro.dev;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import de.goeuro.dev.dsl.geo.Results;
import de.goeuro.dev.App;
import de.goeuro.dev.util.CsvWriter;
import de.goeuro.dev.util.HttpsConnector;

/**
 * Hello world!
 * 
 */
public class App {
	private static final Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {

		if((args.length > 2 ) || (args.length > 1 && !args[1].equals("simulation"))){
			logger.error("main(String[]) - Too much arguments"); //$NON-NLS-1$
			System.exit(-1);
		}
		
		if (args.length > 0) {
			HttpsConnector hc = new HttpsConnector();
			StringBuffer sb = hc.getJson(args[0]);

			String jsonString = null;
			String simulationString = "{\"results\" : [ {\"_type\" : \"Position\",\"_id\" : 410978,\"name\" : \"Potsdam, USA\",\"type\" : \"location\",\"geo_position\" : {\"latitude\" : 44.66978,\"longitude\" : -74.98131} }, {\"_type\" : \"Position\",\"_id\" : 377078,\"name\" : \"Potsdam, Deutschland\",\"type\" : \"location\", \"geo_position\" : { \"latitude\" : 52.39886, \"longitude\" : 13.06566 } } ]}";

			// print result
			if (sb != null) {
				jsonString = sb.toString();
			} else if (args.length == 2 && args[1].equals("simulation")) {
				logger.error("main(String[]) - Error. We are going in Simulation Mode"); //$NON-NLS-1$
				jsonString = simulationString;
			} 

			if (logger.isDebugEnabled()) {
				logger.debug("main(String[]) - " + jsonString); //$NON-NLS-1$
			}
			Results results = new Gson().fromJson(jsonString, Results.class);

			CsvWriter.writeCSV(results);
		} else {
			logger.error("main(String[]) - Please insert a string"); //$NON-NLS-1$
		}
	}
}

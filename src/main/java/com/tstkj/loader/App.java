package com.tstkj.loader;

import java.io.IOException;
import java.util.Properties;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tstkj.loader.bolt.AddNewTemplateBolt;
import com.tstkj.loader.spout.AddNewTemplateSpout;
import com.tstkj.loader.startup.PackageScan;
import com.tstkj.loader.utils.PathConstant;

/**
 * Hello world!
 *
 */
public class App {
	static {
		//
		Properties p = new Properties();
		try {
			p.load(App.class.getClassLoader().getResourceAsStream("config.properties"));
			PathConstant.DEPENDENY_PATH = p.getProperty("dependenypath");
			PathConstant.PACKAGE_PATH = p.getProperty("packagepath");
			PathConstant.BIN_PACKAGE_PAHT = p.getProperty("packagepath").replace("/", ".");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		try {
			context.register(BeanConfig.class);
			context.refresh();
			PackageScan scan = new PackageScan();
			scan.scanPackage();
			TopologyBuilder builder = new TopologyBuilder();
			builder.setSpout("1", new AddNewTemplateSpout());
			builder.setBolt("2", new AddNewTemplateBolt()).shuffleGrouping("1");
			Config conf = new Config();
			conf.setDebug(false);
			StormSubmitter.submitTopology("topo", conf, builder.createTopology());
//			LocalCluster cluster = new LocalCluster();
//			cluster.submitTopology("topo", conf, builder.createTopology());
		} finally {
			context.close();
		}

	}
}

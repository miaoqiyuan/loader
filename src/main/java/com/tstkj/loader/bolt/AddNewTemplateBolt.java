package com.tstkj.loader.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

public class AddNewTemplateBolt extends BaseRichBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1178584447624092115L;

	@Override
	public void execute(Tuple arg0) {
		System.out.println("++++++++++++++++++++++++++++++++++++++"+ arg0.getString(0));
	}

	@Override
	public void prepare(@SuppressWarnings("rawtypes") Map arg0, TopologyContext arg1, OutputCollector arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}

}

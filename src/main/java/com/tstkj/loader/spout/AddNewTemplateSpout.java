package com.tstkj.loader.spout;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class AddNewTemplateSpout extends BaseRichSpout {

	private SpoutOutputCollector collector;

	/**
	 * 
	 */
	private static final long serialVersionUID = 658453618648202229L;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		// TODO Auto-generated method stub
		this.collector = collector;
	}

	@Override
	public void nextTuple() {
		// TODO Auto-generated method stub
		String name = "Template_1";
		collector.emit(new Values(name));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("name"));
	}

}

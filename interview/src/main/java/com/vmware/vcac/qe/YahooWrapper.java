package com.vmware.vcac.qe;

import java.util.Arrays;

/**
 * A Wrapper class for JSON deserialization of the Yahoo JSON response.
 * 
 * @author willwallace
 *
 */
public class YahooWrapper {
	private List list;

	public List getList() {
		return list;
	}
}

// This not an ideal name for this class, but it is named by the Json from
// yahoo, which is not a java.util.List
final class List {
	private Meta meta;
	private Resource[] resources;

	public Meta getMeta() {
		return meta;
	}

	public Resource[] getResources() {
		return resources;
	}

	@Override
	public String toString() {
		return "List [meta=" + meta + ", resources=" + Arrays.toString(resources) + "]";
	}

}

final class Meta {
	private String type;
	private int start;
	private int count;

	public String getType() {
		return type;
	}

	public int getStart() {
		return start;
	}

	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "Meta [type=" + type + ", start=" + start + ", count=" + count + "]";
	}

}

final class Resource {
	private String classname;
	private Fields fields;

	public String getClassname() {
		return classname;
	}

	public Fields getFields() {
		return fields;
	}

	@Override
	public String toString() {
		return "Resource [classname=" + classname + ", fields=" + fields + "]";
	}

}

final class Fields {
	private String name;
	private float price;
	private String symbol;
	private int ts;
	private String type;
	private String date; // should be date
	private int volume;

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public String getSymbol() {
		return symbol;
	}

	public int getTs() {
		return ts;
	}

	public String getType() {
		return type;
	}

	public String getDate() {
		return date;
	}

	public int getVolume() {
		return volume;
	}

	@Override
	public String toString() {
		return "Fields [name=" + name + ", price=" + price + ", symbol=" + symbol + ", ts=" + ts + ", type=" + type
				+ ", date=" + date + ", volume=" + volume + "]";
	}

}

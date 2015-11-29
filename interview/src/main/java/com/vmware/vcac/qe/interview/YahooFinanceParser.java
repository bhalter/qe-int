package com.vmware.vcac.qe.interview;
// This class parse the response and returns the VMWare stock price from yahoo finance.
// The response is XML 

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class YahooFinanceParser implements IVmwFinanceParser{

	//Tag in xml document corresponding to current Stock price
	static String stockPriceTag = "LastTradePriceOnly";
	
	// Tag representing the quote tag in xml response
	static String quoteTag = "quote";
	
	
	// function to parse the xml response
	// @params: xmlResYahoo
	// @return: Yahoo Stock price
	public double parseAndGetValue(String xmlResYahoo) 
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try 
		{
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xmlResYahoo.getBytes("utf-8"))));
			NodeList s = doc.getElementsByTagName(quoteTag);
			Node nNode = s.item(0);
			Element eElement = (Element) nNode;
			// LastTradePriceOnly tag represents the Stock price in response XML
			return Double.parseDouble(eElement.getElementsByTagName(stockPriceTag).item(0).getTextContent().trim());
		} 
		catch (ParserConfigurationException e) 
		{
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return 0;
	}

}

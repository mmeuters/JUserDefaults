/*
Copyright (c) 2013 Marlon Meuters

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package userdefaults;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.json.*;

/**
 * JUserDefaults provides a simple JSON based key value store. It was influenced
 * by NSUserDefaults.
 * JUserDefaults is implemented as a singleton and can be accessed using the
 * static methods <code>getInstance()</code> and <code>getStandardUserDefaults()</code>.
 * Set values/objects unsing <code>put(String k, Object v)</code>. Access using
 * <code>get(String k)</code>. Make changes persistent using <code>synchronize()</code>.
 *
 * @author Marlon Meuters (CLUSTER ONE GmbH)
 * @version 2013-07-31
 */

public class JUserDefaults {
	
	/**
	 * JUserDefaults is implemented as a singleton.
	 */
	private static JUserDefaults sharedInstance = null;
	
	final static String storageFileName = ".userDefaultsStore";
	
	/**
     * Returns the <code>JUserDefaults</code> singleton object. 
     */
	public static JUserDefaults getInstance()
	{
		if (sharedInstance == null) {
			sharedInstance = new JUserDefaults();
		}
		return sharedInstance;
	}
	
	/**
     * See <code>getInstance()</code>
     */
	public static JUserDefaults getStandardUserDefaults()
	{
		return JUserDefaults.getInstance();
	}
	
	/**
	 * i vars
	 */
	private org.json.JSONObject storageObject = null;
	
	private JUserDefaults() 
	{
		loadStorage();
		if (this.storageObject == null) {
			this.storageObject = new JSONObject();
			this.storageObject.put("storageName", "standardUserDefaults");
			this.storageObject.put("keyValueStore", new JSONObject());
			synchronize();
		}
	}
	
	private void loadStorage() {
		File file = new File(storageFileName);
		String completeFileString = "";
		try{  	
		  	FileInputStream fstream = new FileInputStream(file.getAbsolutePath());
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			while ((strLine = br.readLine()) != null) {
				completeFileString = completeFileString + strLine + "\n";
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		
		try {
			this.storageObject = new org.json.JSONObject(completeFileString);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	private JSONObject getKeyValueStore() 
	{
		return this.storageObject.getJSONObject("keyValueStore");
	}

	/**
     * Makes changes persistent by writing them to a file.
     */
	public void synchronize() 
	{
		try {
			if (this.storageObject != null) {
				File f = new File(storageFileName);
		    	FileWriter fstream = new FileWriter(f);
		    	BufferedWriter fout = new BufferedWriter(fstream);
				Writer writer = this.storageObject.write(fout);
				writer.flush();
		    	fout.close();
			}
		} catch (IOException e) {
			System.err.print(e.getMessage());
		}
	}
	
	/**
	 * Accessing objects and values in the store
	 */
	
	public Object get(String key) { 
		return getKeyValueStore().get(key); 
	}
	
	public String getString(String key) {
		return getKeyValueStore().getString(key);
	}
	
	public boolean getBoolean(String key) {
		return getKeyValueStore().getBoolean(key);
	}
	
	public int getInt(String key) {
		return getKeyValueStore().getInt(key);
	}
	
	public double getDouble(String key) {
		return getKeyValueStore().getDouble(key);
	}

	public Object[] getArray(String key) {
		JSONArray jsonArray = getKeyValueStore().getJSONArray(key);
		Object[] a = new Object[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
			a[i] = jsonArray.get(i);
		}
		return a;
	}

	public Map<String, Object> getMap(String key) {
		JSONObject jsonObject = getKeyValueStore().getJSONObject(key);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String[] keys = JSONObject.getNames(jsonObject);
		for (int i = 0; i < keys.length; i++) {
			String k = keys[i];
			Object v = jsonObject.get(k);
			map.put(k, v);
		}
		return map;
	}
	
	/**
	 * Setting objects and values to the store
	 */
	
	public void put(String k, Object v) {
		getKeyValueStore().put(k,v);
	}
	
	public void put(String k, String v) {
		getKeyValueStore().put(k,v);
	}
	
	public void put(String k, boolean v) {
		getKeyValueStore().put(k,v);
	}
	
	public void put(String k, int v) {
		getKeyValueStore().put(k,v);
	}
	
	public void put(String k, double v) {
		getKeyValueStore().put(k,v);
	}
	
	public void put(String k, Object[] v) {
		getKeyValueStore().put(k,v);
	}
	
	public void put(String k, Map<?,?> v) {
		getKeyValueStore().put(k,v);
	}
	
	/**
	 * Checking if a key/value pair exists
	 */
	
	public boolean has(String k) {
		return getKeyValueStore().has(k);
	}
	
	/**
	 * Removing a key/value pair
	 */
	
	public void remove(String k) {
		if (has(k) == true) {
			getKeyValueStore().remove(k);
		}
	}
}

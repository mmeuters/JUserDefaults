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

package juserdefaults;

import java.util.HashMap;
import java.util.Map;

public class JUserDefaultsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JUserDefaults userDefaults = JUserDefaults.getStandardUserDefaults();
		
		// Writing
		userDefaults.put("a", 1);
		userDefaults.put("b", 2);
		userDefaults.synchronize();
		
		userDefaults.put("c", 3);
		userDefaults.synchronize();
		
		int[] array1 = {10,20,30,40,50};
		userDefaults.put("array1", array1);
		userDefaults.synchronize();
		
		userDefaults.put("really?", true);
		userDefaults.synchronize();
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("whatever1", "value1");
		m.put("whatever2", 123);
		m.put("whatever3", 1000000000000L);
		m.put("whatever4", true);
		m.put("whatever5", array1);
		userDefaults.put("aMap", m);
		userDefaults.synchronize();
		
		
		// Reading
		System.out.println("Reading some values");
		System.out.println("a = " + userDefaults.get("a"));
		System.out.println("b = " + userDefaults.get("b"));
		System.out.println("c = " + userDefaults.get("c"));
		System.out.println("array1 = " + userDefaults.get("array1"));
		System.out.println("really? = " + userDefaults.get("really?"));
		System.out.println("aMap = " + userDefaults.get("aMap"));
		
		System.out.println();
		System.out.println("Checking if store has values for keys");
		System.out.println("a?  " + userDefaults.has("a"));
		System.out.println("d?  " + userDefaults.has("d"));
		System.out.println("randomkey?  " + userDefaults.has("randomkey"));
		System.out.println("aMap?  " + userDefaults.has("aMap"));
	}

}

package project3;
import java.util.Arrays;


public class Test {

	/**
	 * Creates an array of keys to words to be added and/or deleted to/from 
	 * a Persistent Binary Search Tree. After they are added and/or deleted,
	 * the tree is then printed out in the sorted order
	 */
	public static void testNamesBST()
	{
		String[] keys = {"first", "last"};
		
		for (int j = 0; j < keys.length; j++) {
		
		PersistentBST t = new PersistentBST(keys[j]);

		t.insert("{ \"first\" : \"David\" , \"last\" : \"Smith\"}");
		t.insert("{ \"first\" : \"Allan\" , \"last\" : \"Green\"}");
		t.insert("{ \"first\" : \"Greg\" , \"last\" : \"Allan\"}");
		t.insert("{ \"first\" : \"Bob\" , \"last\" : \"Cortez\"}");
		t.insert("{ \"first\" : \"Quan\" , \"last\" : \"Chang\"}");
		t.insert("{ \"first\" : \"Ceya\" , \"last\" : \"Mateo\"}");
		
		t.delete("{ \"first\" : \"David\" , \"last\" : \"Smith\"}");
		t.delete("{ \"first\" : \"Allan\" , \"last\" : \"Green\"}");
		t.insert("{ \"first\" : \"last\" , \"last\" : \"first\"}");
		t.insert("{ \"first\" : \"Bale\" , \"last\" : \"Zern\"}");
		t.insert("{ \"first\" : \"Arix\" , \"last\" : \"Miu\"}");
		t.insert("{ \"first\" : \"Cron\" , \"last\" : \"Koradou\"}");
		t.insert("{ \"first\" : \"Amon\" , \"last\" : \"Chamra\"}");
		t.insert("{ \"first\" : \"Zechs\" , \"last\" : \"Seumic\"}");
		t.delete("{ \"first\" : \"Cron\" , \"last\" : \"Koradou\"}");
		t.delete("{ \"first\" : \"Greg\" , \"last\" : \"Allan\"}");
		t.insert("{ \"first\" : \"Cander\" , \"last\" : \"Jhcor\"}");

		for (int i = 0; i <= t.currentTime(); i++) {

			System.out.print(t.find("{ \"first\" : \"David\" , \"last\" : \"Smith\"}", i));
			System.out.println(Arrays.toString(t.getAllElements(i)));
		}
		
		System.out.println("---------------------------------------------");
		}	
	}
	
	/**
	 * Creates a Persistent Stack with letters to be added and deleted.
	 * After the letters are added and/or removed, the Persistent Stack
	 * is then printed out either in order or reverse order
	 */
	public static void testStack() {
		
		PersistentStack S = new PersistentStack();
		
		S.push("A");
		S.push("B");
		S.push("C");
		S.push("D");
		S.pop();
		S.pop();
		S.push("E");
		S.push("F");
		S.push("G");
		S.pop();
		S.pop();
		S.pop();
		S.push("H");
		S.push("I");
		S.pop();
		S.push("J");
		
		for (int i = 0; i <= S.currentTime(); i++) {
			System.out.println(Arrays.toString(S.getAllElements(i, true)));
		}
	}
	
	/**
	 * Creates an array of keys to rest of string to be added and/or deleted to/from 
	 * a Persistent Binary Search Tree. After they are added and/or deleted,
	 * the tree is then printed out in the sorted order after key
	 */
	public static void testAbstractBST() {
		
		String[] keys = {"A", "B", "C", "D"};
		
		for (int j = 0; j < keys.length; j++) {
			
			PersistentBST t = new PersistentBST(keys[j]);
			
			t.insert("{\"A\":\"C\",  \"B\" : \"A\",\"C\" : \"D\", \"D\" : \"A\"  }");
			t.insert("{\"A\":\"A\",  \"B\" : \"B\",\"C\" : \"D\", \"D\" : \"I\"  }");
			t.insert("{\"A\":\"B\",  \"B\" : \"C\",\"C\" : \"D\", \"D\" : \"B\"  }");
			t.insert("{\"A\":\"D\",  \"B\" : \"D\",\"C\" : \"D\", \"D\" : \"J\"  }");
			t.insert("{\"A\":\"H\",  \"B\" : \"E\",\"C\" : \"D\", \"D\" : \"C\"  }");
			t.insert("{\"A\":\"I\",  \"B\" : \"F\",\"C\" : \"D\", \"D\" : \"K\"  }");
			t.insert("{\"A\":\"F\",  \"B\" : \"G\",\"C\" : \"D\", \"D\" : \"D\"  }");
			t.insert("{\"A\":\"G\",  \"B\" : \"H\",\"C\" : \"D\", \"D\" : \"L\"  }");
			
			t.delete("{\"A\":\"C\",  \"B\" : \"A\",\"C\" : \"D\", \"D\" : \"A\"  }");
			t.delete("{\"A\":\"I\",  \"B\" : \"F\",\"C\" : \"D\", \"D\" : \"K\"  }");
						
			t.insert("{\"A\":\"E\",  \"B\" : \"I\",\"C\" : \"D\", \"D\" : \"E\"  }");
			t.insert("{\"A\":\"K\",  \"B\" : \"J\",\"C\" : \"D\", \"D\" : \"M\"  }");
			t.insert("{\"A\":\"L\",  \"B\" : \"K\",\"C\" : \"D\", \"D\" : \"F\"  }");
			t.insert("{\"A\":\"J\",  \"B\" : \"L\",\"C\" : \"D\", \"D\" : \"N\"  }");
			t.insert("{\"A\":\"N\",  \"B\" : \"M\",\"C\" : \"D\", \"D\" : \"G\"  }");

			t.delete("{\"A\":\"L\",  \"B\" : \"K\",\"C\" : \"D\", \"D\" : \"F\"  }");
			
			t.insert("{\"A\":\"M\",  \"B\" : \"N\",\"C\" : \"D\", \"D\" : \"O\"  }");
			t.insert("{\"A\":\"P\",  \"B\" : \"O\",\"C\" : \"D\", \"D\" : \"H\"  }");
			t.insert("{\"A\":\"O\",  \"B\" : \"P\",\"C\" : \"D\", \"D\" : \"P\"  }");
			
			for (int i = 0; i <= t.currentTime(); i++) {
				System.out.println(Arrays.toString(t.getAllElements(i)));
			}			
			System.out.println("---------------------------------------------");
		}
	}
	
	
	public static void main(String argsp[]) {
		testStack();
		testNamesBST();
		testAbstractBST();
	}
}
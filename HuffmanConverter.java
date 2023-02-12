package HuffmanEncoderP2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class HuffmanConverter{
	public static final int NUMBER_OF_CHARACTERS = 256;
	String contents;
	HuffmanTree hT;
	private int count[];
	private String code[];
	public HuffmanConverter(String input) {
		this.contents = input;
		this.count = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
	}
	public void recordFrequencies() {
		for (int i=0; i<contents.length(); i++) {
			count[(int)contents.charAt(i)] += 1;
		}
	}
	public void frequenciesToTree() {
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>();
		for (int i=0; i<256; i++) {
			if (count[i] != 0) {
				String s = ""+(char)i;
				Double d = (double) count[i];
				HuffmanNode node = new HuffmanNode(s, d);
				heap.insert(node);
			}
		}
		heap.printHeap();
		hT = HuffmanTree.createFromHeap(heap);
	}
	public void treeToCode() {
		for(int i=0; i<256; i++) {
			code[i] = "";
		}
		treeToCode(hT.root, "");
	}
	private void treeToCode(HuffmanNode t, String s) {
		if (t.letter.length() > 1) {
			treeToCode(t.left, s + "0");
			treeToCode(t.right, s + "1");
		} 
		else if (t.letter.length() == 1) {
			code[(int)t.letter.charAt(0)] = s;
		}
	}
	public String encodeMessage() {
		String message = "";
		for (int i=0; i<contents.length(); i++) {
			message += code[(int)contents.charAt(i)];
		}
		return message;
	}
	public static String readContents(String filename) {
		String temp = "";
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				temp+= sc.nextLine();
				temp += "\n";
			}
			sc.close();
			return temp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}
	public String decodeMessage(String encodedStr) {
		String toCheck = "";
		String decoded = "";
		while (!encodedStr.isEmpty()) {
			toCheck += ""+encodedStr.charAt(0);
			encodedStr = encodedStr.substring(1);
			if (Arrays.asList(code).contains(toCheck)) {
				decoded += ""+(char)findIndexOfCode(toCheck);
				toCheck = "";
			}
		}
		return decoded;
	}
	
	private int findIndexOfCode(String encoded) {
		for (int i=0; i<code.length; i++) {
			if (code[i].equals(encoded)) {
				return i;
			}
		}
		return -1;
    }
}

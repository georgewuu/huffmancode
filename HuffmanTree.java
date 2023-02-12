package HuffmanEncoderP2;
import java.util.Scanner;
public class HuffmanTree {
    HuffmanNode root;
    public HuffmanTree(HuffmanNode huff){
        this.root = huff;
    }
    public void printLegend(){
        printLegend(root, "");
    }
    private void printLegend(HuffmanNode t, String s){
        if(t.letter.length() == 1){
            System.out.println(t.letter+"="+s);
        }
        if(t.letter.length() >1){
            printLegend(t.left, s+"0");
            printLegend(t.right, s+"1");
        }
       
    }

    public static BinaryHeap legendToHeap(String legend){
        BinaryHeap BH = new BinaryHeap();
        String[] s = legend.split(" ");
        for(int i = 0;i<s.length;i+=2){
            String letter = s[i];
            Double f = Double.parseDouble(s[i+1]);
            BH.insert(new HuffmanNode(letter, f));
        }
        return BH;

        
    }  
    public static HuffmanTree createFromHeap(BinaryHeap b){
        while(b.getSize()!=1){
            HuffmanNode n1 = (HuffmanNode) b.deleteMin();
            HuffmanNode n2 = (HuffmanNode) b.deleteMin();
            HuffmanNode parent = new HuffmanNode(n1,n2);
            parent.left = n1;
            parent.right = n2;
            b.insert(parent);
        }
        HuffmanTree tree = new HuffmanTree((HuffmanNode)b.findMin());
        return tree;
    }
   
}

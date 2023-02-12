package HuffmanEncoderP2;
public class test {
    public static void main(String[] args){
        String s = HuffmanConverter.readContents("/Users/george/Desktop/2022 DataStructure HW/HuffmanEncoderP1/HuffmanEncoderP2/love_poem_80.txt");
        HuffmanConverter HC = new HuffmanConverter(s);
        HC.recordFrequencies();
        HC.frequenciesToTree();
        HC.hT.printLegend();
        System.out.println("Huffman Encoding: ");
        HC.treeToCode();
        String enc = HC.encodeMessage();
        System.out.println(enc +"\n");
        System.out.println("Message size in ASCII encoding: "+HC.contents.length()*8);
        System.out.println("Message size in Huffman coding: "+ enc.length());
        System.out.println(HC.decodeMessage(enc));
    }
}

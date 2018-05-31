package IO;

import java.io.IOException;
import java.util.*;

public class HuffmanTree {
    private Node root;
    private List<Byte> coded_data;
    //Byte num_of_nodes;

    /**
     * c'tor
     * @param byte_list - data about number and frequences
     */
    HuffmanTree(List<Byte> byte_list) {
        //creating the tree
        HashMap<Byte, Integer> frequencies = new HashMap<>();
        for (Byte b : byte_list) {
            frequencies.put(b, frequencies.containsKey(b) ? frequencies.get(b) + 1 : 1);//if new put 1, else +1
        }
        //in any iteration we take the 2 node with the smallest frequencies and make them brother.
        PriorityQueue<Node> trees = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : frequencies.entrySet()) {
            trees.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (trees.size() != 1) {
            trees.add(new Node(null, trees.poll(), trees.poll()));
        }
        //the root is the last node in the queue
        root = trees.poll();
        //creating the compressed byte array with the huffman tree
        HashMap<Byte, List<Byte>> codes = root.getTreeCodes();
        coded_data = new LinkedList<>();
        for (Byte b : byte_list) {
            coded_data.addAll(codes.get(b));
        }


    }
    //Build the Huffman according to file bits.
    HuffmanTree(BitInputStream bis) {
        root = ReadNode(bis);
    }

    /**
     * This function create node according to the readen bit. if is 0 create inner node, if 1 create leaf.
     * @param reader - stream e red with.
     * @return the new node according the condition above.
     */
    private Node ReadNode(BitInputStream reader) {
        try {
            int bit = reader.readBits(1);
            if (bit == 1) {
                return new Node((byte) reader.readBits(8), 0);
            } else if (bit == 0) {
                Node leftChild = ReadNode(reader);
                Node rightChild = ReadNode(reader);
                return new Node(null, leftChild, rightChild);
            } else
                return null;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * return representation of the tree by list of bytes.
     * @return representation of the tree by list of bytes.
     */
    List<Byte> getCompressedTree() {
        List<Byte> ugly_comp_tree = new LinkedList<>();
        getCompressedTree_Helper(root, ugly_comp_tree);//return bytes without compress by 8 to 1
        byte[] buffer = new byte[ugly_comp_tree.size()];
        int[] bit_index = {0};
        prettify_ugly_tree(buffer, bit_index, ugly_comp_tree.iterator());//compress 8 to 1
        int size = (int) Math.ceil((double) bit_index[0] / 8) + 1;//how many cell is for the tree data

        int k = ((int) Math.ceil((double) size / 255)) - ((size % 255 == 0 ) ? 1 : 0) + 1;//note that size!=0
        Byte[] answer = new Byte[size + k];
        if (size % 255 != 0)//fill the buffer with data.
            answer[0] = (byte) (size % 255);
        for (int i = 1 - (size % 255 == 0 ? 1 : 0); i < k - 1; i++) {
            answer[i] = (byte) 255;
        }
        answer[k - 1] = 0;
        for (int i = k; i < answer.length - 1; i++) {
            answer[i] = buffer[i - k];
        }
        answer[answer.length - 1] = (byte) (bit_index[0] % 8);
        return Arrays.asList(answer);
    }

    /**
     * return the data of the tree(the value of each node and the path to him)
     * @return the data of the tree(the value of each node and the path to him)
     */
    List<Byte> getCompressedData() {
        Byte[] comp_maze = new Byte[((coded_data.size() + 7) / 8) + 1];
        int bit_index = 0;
        Iterator<Byte> iterator = coded_data.iterator();
        //compress the daata in 8 to 1 method
        for (int current_index = 0; current_index < coded_data.size(); current_index++, bit_index++) {
            byte b = comp_maze[bit_index / 8] == null ? 0 : comp_maze[bit_index / 8];
            b |= (iterator.next() & 0xFF) << (7 - (bit_index % 8));
            comp_maze[bit_index / 8] = b;
        }
        comp_maze[comp_maze.length - 1] = (byte) (bit_index % 8);//if modulo eq 0 check
        return Arrays.asList(comp_maze);
    }

    /**
     *Return compress presentation in take 8 bits and make him as 1
     * @param buffer - the buffer contain the data
     * @param bit_index -
     * @param iterator - the pointer to the tree
     */
    private void prettify_ugly_tree(byte[] buffer, int[] bit_index, Iterator<Byte> iterator) {
        byte indicator = iterator.next();

        buffer[bit_index[0] / 8] |= (indicator) << (7 - (bit_index[0] % 8));
        bit_index[0]++;

        //the node is leaf and we need to compress the value.
        if (indicator == 1) {
            byte val = iterator.next();
            buffer[bit_index[0] / 8] |= (val & 0xFF) >> (bit_index[0] % 8);
            int shift = 8 - (bit_index[0] % 8);

            buffer[(bit_index[0] / 8) + 1] = (byte) ((val & 0xFF) << shift);

            bit_index[0] += 8;
        } else { // indicator == 0
            prettify_ugly_tree(buffer, bit_index, iterator);
            prettify_ugly_tree(buffer, bit_index, iterator);
        }
    }

    /**
     * return the data about the tree. Every node and his data and path.
     * @param root - the root of the tree
     * @param result_list - the list we put into
     */
    private void getCompressedTree_Helper(Node root, List<Byte> result_list) {
        if (root.IsLeafNode()) {
            result_list.add((byte) 1);
            result_list.add(root.Value);
        } else {
            result_list.add((byte) 0);
            getCompressedTree_Helper(root.LeftChild, result_list);
            getCompressedTree_Helper(root.RightChild, result_list);
        }
    }

    /**
     * Return the data that the tree is contain
     * @param bis - the stream we read from
     * @param code_size - the size
     * @return list of the data we return
     * @throws IOException - if there is problem with the stream.
     */
    List<Byte> getCoded_data(BitInputStream bis, int code_size) throws IOException {
        List<Byte> answer = new LinkedList<>();
        Node current = root;
        for (int i = 0; i < code_size; i++) {
            try {
                int bit = bis.readBits(1);
                if (bit == 0)//0 is the right node
                    current = current.LeftChild;
                else if (bit == -1) {//end of file
                    return answer;
                } else// 1 is left child
                    current = current.RightChild;
            } catch (IOException e) {
                return answer;
            }
            if (current.IsLeafNode()) {//if node add the value
                answer.add(current.Value);
                current = root;
            }
        }
        return answer;
    }
}

/**
 * This class Represents NOde in huffman tree
 */
class Node implements Comparable<Node> {
    Byte Value; // the number he saved
    Node LeftChild; // the left Node child
    Node RightChild;// the right Node child
    private int Frequency;
    private List<Byte> code;

    /**
     * c'tor
     * @param value - the number he saved
     * @param leftChild - left child of node
     * @param rightChild - right child of node
     */
    Node(Byte value, Node leftChild, Node rightChild) {
        code = new LinkedList<>();
        Value = value;
        LeftChild = leftChild;
        RightChild = rightChild;
        Frequency = rightChild.Frequency + leftChild.Frequency; // in huffman tree the frequency of inner node is the sum of the frequencies of his children
    }

    /**
     * c'tor of leaf
     * @param value - value of the node.
     * @param frequency -
     */
    Node(Byte value, int frequency) {
        code = new LinkedList<>();
        Value = value;
        LeftChild = null;
        RightChild = null;
        Frequency = frequency;
    }

    /**
     * return hashMap of the representation if the tree
     * @return return hashMap of the representation if the tree
     */
    HashMap<Byte, List<Byte>> getTreeCodes() {
        HashMap<Byte, List<Byte>> hashMap = new HashMap<>();
        setTreeCodes(hashMap);
        return hashMap;

    }

    /**
     * Build the representation. Before leftchild put 0 and before right child put 1.
     * @param hashMap -
     */
    private void setTreeCodes(HashMap<Byte, List<Byte>> hashMap) {
        if (IsLeafNode())
            hashMap.put(Value, code);
        else {
            LeftChild.code.addAll(code);
            LeftChild.code.add((byte) 0);
            LeftChild.setTreeCodes(hashMap);
            RightChild.code.addAll(code);
            RightChild.code.add((byte) 1);
            RightChild.setTreeCodes(hashMap);
        }
    }

    /**
     * in Huffman tree, if you inner node you must have to children. if you node you do not have children
     * @return if the nide is node.
     */
    Boolean IsLeafNode() {
        return LeftChild == null;
    }

    /**
     * Compare between to NOde according to they frequancy.
     * @param o - other node.
     * @return this.Frequency - o.Frequency
     */
    @Override
    public int compareTo(Node o) {
        return this.Frequency - o.Frequency;
    }
}


package IO;

import java.io.IOException;
import java.util.*;

public class HuffmanTree {
    Node root;
    List<Byte> coded_data;
    //Byte num_of_nodes;

    public HuffmanTree(List<Byte> byte_list) {
        //creating the tree
        HashMap<Byte, Integer> frequencies = new HashMap<>();
        for (Byte b : byte_list) {
            frequencies.put(b, frequencies.containsKey(b) ? frequencies.get(b) + 1 : 1);
        }
        //num_of_nodes = (byte) frequencies.size();
        //change to PriorityQueue I guess
        LinkedList<Node> trees = new LinkedList<>();
        for (Map.Entry<Byte, Integer> entry : frequencies.entrySet()) {
            trees.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (trees.size() != 1) {
            trees.sort(Node::compareTo);
            trees.add(new Node(null, trees.pollFirst(), trees.pollFirst()));
        }
        root = trees.get(0);
        //creating the compressed byte array with the huffman tree

//        getCompressedTree();
        HashMap<Byte, List<Byte>> codes = root.getTreeCodes();
        coded_data = new LinkedList<>();
        for (Byte b : byte_list) {
            coded_data.addAll(codes.get(b));
        }


    }

    public HuffmanTree(BitInputStream bis) {
        root = ReadNode(bis);

    }

    Node ReadNode(BitInputStream reader) {
        try {
            int bit = reader.readBits(1);
            if (bit == 1) {
                return new Node((byte) reader.readBits(8), 0);
            } else if (bit == 0) {
                Node leftChild = ReadNode(reader);
                Node rightChild = ReadNode(reader);
                return new Node(null, leftChild, rightChild);
            } else
                return null;//?

        } catch (IOException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public List<Byte> getCompressedTree() {
        List<Byte> ugly_comp_tree = new LinkedList<>();
        getCompressedTree_Helper(root, ugly_comp_tree);
        byte[] buffer = new byte[ugly_comp_tree.size()];
        int[] bit_index = {0};
        prettify_ugly_tree(buffer, bit_index, ugly_comp_tree.iterator());
        //Byte[] answer = new Byte[((bit_index[0] + 7) / 8) + 2];
        int size = (int) Math.ceil((double) bit_index[0] / 8) + 1;

        int k = ((int) Math.ceil((double) size / 255)) - ((size % 255 == 0 ) ? 1 : 0) + 1;//note that size!=0
        Byte[] answer = new Byte[size + k];
        //System.arraycopy(buffer, 0, answer, 0, answer.length - 1);
        //for (int i = 1; i < answer.length - 1; i++) {
        if (size % 255 != 0)
            answer[0] = (byte) (size % 255);
        int iSize = size - (size % 255);
        for (int i = 1 - (size % 255 == 0 ? 1 : 0); i < k - 1; i++) {
            answer[i] = (byte) 255;
            iSize -= 255;
        }
        answer[k - 1] = 0;
        for (int i = k; i < answer.length - 1; i++) {
            //answer[i] = buffer[i - 1];
            answer[i] = buffer[i - k];
            //System.out.println(answer[i] & 0xFF);
        }
        //answer[0] = num_of_nodes;
        answer[answer.length - 1] = (byte) (bit_index[0] % 8);
        return Arrays.asList(answer);
    }

    public List<Byte> getCompressedData() {
        Byte[] comp_maze = new Byte[((coded_data.size() + 7) / 8) + 1];
        int bit_index = 0;
        Iterator<Byte> iterator = coded_data.iterator();
        for (int current_index = 0; current_index < coded_data.size(); current_index++, bit_index++) {
            byte b = comp_maze[bit_index / 8] == null ? 0 : comp_maze[bit_index / 8];
            b |= (iterator.next() & 0xFF) << (7 - (bit_index % 8));
            comp_maze[bit_index / 8] = b;
        }
        comp_maze[comp_maze.length - 1] = (byte) (bit_index % 8);//if modulo eq 0 check
        return Arrays.asList(comp_maze);
    }

    private void prettify_ugly_tree(byte[] buffer, int[] bit_index, Iterator<Byte> iterator) {
        byte indicator = iterator.next();

        buffer[bit_index[0] / 8] |= (indicator) << (7 - (bit_index[0] % 8));
        bit_index[0]++;

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

    public List<Byte> getCoded_data(BitInputStream bis, int code_size) throws IOException {
        List<Byte> answer = new LinkedList<>();
        Node current = root;
        for (int i = 0; i < code_size; i++) {
//            if (bis.available() == 0)
//                System.out.println("pass");
//            if (current == null)
//                System.out.println("im null");

            try {
                int bit = bis.readBits(1);
                if (bit == 0)
                    current = current.LeftChild;
                else if (bit == -1) {
                    return answer;
                } else
                    current = current.RightChild;
            } catch (IOException e) {
                return answer;
            }
            if (current.IsLeafNode()) {
                answer.add(current.Value);
                current = root;
//                continue;
            }
        }
        return answer;
    }
}

class Node implements Comparable<Node> {
    public Byte Value;
    public Node LeftChild;
    public Node RightChild;
    public int Frequency;
    public List<Byte> code;

    public Node(Byte value, Node leftChild, Node rightChild) {
        code = new LinkedList<>();
        Value = value;
        LeftChild = leftChild;
        RightChild = rightChild;
        Frequency = rightChild.Frequency + leftChild.Frequency;
    }

    public Node(Byte value, int frequency) {
        code = new LinkedList<>();
        Value = value;
        LeftChild = null;
        RightChild = null;
        Frequency = frequency;
    }

    public HashMap<Byte, List<Byte>> getTreeCodes() {
        HashMap<Byte, List<Byte>> hashMap = new HashMap<>();
        setTreeCodes(hashMap);
        return hashMap;

    }

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


    public int getHeight() {
        if (IsLeafNode())
            return 1;
        return 1 + Math.max(this.LeftChild.getHeight(), RightChild.getHeight());
    }

    public Boolean IsLeafNode() {
        return LeftChild == null;
    }

    @Override
    public int compareTo(Node o) {
        return this.Frequency - o.Frequency;
    }
}


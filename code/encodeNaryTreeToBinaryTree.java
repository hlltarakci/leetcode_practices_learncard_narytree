// https://leetcode.com/explore/learn/card/n-ary-tree/131/recursion/920/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Pair {
    public Node nNode;
    public TreeNode bNode;
    public Pair(Node nNode, TreeNode bNode) {
        this.nNode = nNode;
        this.bNode = bNode;
    }
}
class Codec {
    /*
        n: num of nodes
        time: O(n)
        space: O() ?
    */
    
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if(root == null) return null;
        
        Queue<Pair> queue = new LinkedList<>();
        TreeNode bRoot = new TreeNode(root.val);
        queue.add(new Pair(root, bRoot));
        while(!queue.isEmpty()) {
            Pair pair = queue.remove();
            Node nNode = pair.nNode;
            TreeNode bNode = pair.bNode;
            
            TreeNode prev=null, head=null;
            for(Node child: nNode.children) {
                TreeNode bChild = new TreeNode(child.val);
                queue.add(new Pair(child, bChild));
                if(head == null) head = bChild;
                else prev.right = bChild;
                prev = bChild;
            }
            
            if(head != null) bNode.left = head;
        }
        
        // printBinaryTree(bRoot);
        return bRoot;
    }
    
    private void printBinaryTree(TreeNode root) {
        if(root == null) return;
        System.out.print(root.val + " ");
        printBinaryTree(root.left);
        printBinaryTree(root.right);
    }
	
    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if(root == null) return null;
            
        Queue<Pair> queue = new LinkedList<>();
        Node nRoot = new Node(root.val, new ArrayList<>());
        queue.add(new Pair(nRoot, root));
        while(!queue.isEmpty()) {
            Pair pair = queue.remove();
            Node nNode = pair.nNode;
            TreeNode bNode = pair.bNode;

            if(bNode.left != null) {
                TreeNode bLeft = bNode.left;
                Node firstChild = new Node(bLeft.val, new ArrayList<>());
                queue.add(new Pair(firstChild, bLeft));
                
                nNode.children.add(firstChild);
                
                TreeNode bRight = bLeft.right;
                while(bRight!= null) {
                    Node sibling = new Node(bRight.val, new ArrayList<>());
                    queue.add(new Pair(sibling, bRight));
                    
                    nNode.children.add(sibling);
                    
                    bRight = bRight.right;
                }
                
            }
        }
        
        // printNaryTree(nRoot);
        
        return nRoot;
    }
    
    private void printNaryTree(Node root) {
        if(root == null) return;
        System.out.print(root.val + " ");
        for(Node child: root.children) 
            printNaryTree(child);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));

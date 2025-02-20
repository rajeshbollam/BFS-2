//In this approach we use BFS and perform level order traversal, but we only add values of nodes to the list which are at the last in the queue.
//Time Complexity: O(n), going through all the nodes in the tree
//Space Complexity: O(n), for queue
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    List<Integer> result;
    public List<Integer> rightSideView(TreeNode root) {
        result = new ArrayList<>();
        if(root == null){ 
            return result;
        }
        bfs(root);
        return result;
    }

    private void bfs(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                TreeNode node = q.poll();
                if(i == size-1){
                    result.add(node.val);
                }

                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
        }
    }
}

//The approach here is to use DFS to traverse the tree, but we first perform the right recursion and add the elements to the result list if the depth of a node is equal to size of the result list.
//This way, we only add the right side view elements and we add only once.
//Time Complexity: O(n) for traversing all the nodes in the tree
//Space Complexity: O(h), where h is the maximum depth from root node to a leaf node

class Solution1 {
    List<Integer> result;
    public List<Integer> rightSideView(TreeNode root) {
        result = new ArrayList<>();
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int depth){
        //base
        if(root == null){
            return;
        }
        //logic
        if(depth == result.size()){
            result.add(root.val);
        }

        dfs(root.right, depth+1);
        dfs(root.left, depth+1);
    }

}

//The approach here is to use DFS to traverse the tree, but we perform left recursion first on the children and add elements to the result list based on the depth of the node and size of the result list
//However, we override the values of the result with new values, once we find a new element from the same level. This provides the result list with the right side view elements of the tree
//Time Complexity: O(n) for traversing all the nodes in the tree
//Space Complexity: O(h), where h is the maximum depth from root node to a leaf node
class Solution2 {
    List<Integer> result;
    public List<Integer> rightSideView(TreeNode root) {
        result = new ArrayList<>();
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode root, int depth){
        //base
        if(root == null){
            return;
        }
        //logic
        if(depth == result.size()){
            result.add(root.val);
        } else {
            result.set(depth, root.val);
        }

        dfs(root.left, depth+1);
        dfs(root.right, depth+1);
        
    }

}
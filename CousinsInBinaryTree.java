//The approach here is to use BFS to check if both the given values are in the same level. 
//We also maintain another queue for parents to make sure that the found values doesn't belong to the same parent.
//Time Complexity: O(n)
//Space Complexity: O(n)
import java.util.LinkedList;
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
    boolean flag;
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> pq = new LinkedList<>();
        q.add(root);
        pq.add(null);
        while(!q.isEmpty()){
            int size = q.size();
            boolean x_found = false;
            boolean y_found = false;
            TreeNode x_parent = null;
            TreeNode y_parent = null;
            for(int i = 0; i<size; i++){
                TreeNode curr = q.poll();
                TreeNode pCurr = pq.poll();
                if(curr.val == x){
                    x_found = true;
                    x_parent = pCurr;
                }
                if(curr.val == y){
                    y_found = true;
                    y_parent = pCurr;
                }
                if(curr.left!=null){
                    q.add(curr.left);
                    pq.add(curr);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                    pq.add(curr);
                }
            }
            if(x_found && y_found){
                return x_parent != y_parent;
            }
            if(x_found || y_found) return false;
        }
        return false;
    }

}

//In this approach, we implement BFS but we check if the given values are cousins without keeping track of the parents.
//At each level, before adding it's children into the queue, we check if both children of a node are given values, if they are, we know they are not cousins and we stop checking further.
//If they are not, then we know that the values found in the queue are definitely cousins.
//Time Complexity: O(n)
//Space Complexity: O(n)

class Solution1 {
    boolean flag;
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> pq = new LinkedList<>();
        q.add(root);
        pq.add(null);
        while(!q.isEmpty()){
            int size = q.size();
            boolean x_found = false;
            boolean y_found = false;
            for(int i = 0; i<size; i++){
                TreeNode curr = q.poll();
                if(curr.val == x){
                    x_found = true;
                }
                if(curr.val == y){
                    y_found = true;
                }

                if(curr.left != null && curr.right != null){
                    if(curr.left.val == x && curr.right.val == y){
                        return false;
                    }
                    if(curr.left.val == y && curr.right.val == x){
                        return false;
                    }
                }

                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
            }
            if(x_found && y_found){
                return true;
            }
            if(x_found || y_found) return false;
        }
        return false;
    }

}

//In this approach, we use DFS traversal and when we find the values, we keep track of the depth of the nodes and their parents as well. 
//We then check that if both values are cousins by checking if the depths match and parents don't match.
//Time Complexity: O(n)
//Space Complexity: O(h), where h is the maximum depth of the tree from root node to the leaf node.
class Solution2 {
    int x_depth;
    int y_depth;
    TreeNode x_parent;
    TreeNode y_parent;
    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, null, 0, x, y);
        return x_depth == y_depth && x_parent!=y_parent;
    }

    private void dfs(TreeNode root, TreeNode parent, int depth, int x, int y){
        //base
        if(root == null) return;

        //logic
        if(root.val == x){
            x_depth = depth;
            x_parent = parent;
        }

        if(root.val == y){
            y_depth = depth;
            y_parent = parent;
        }

        dfs(root.left, root, depth+1, x, y);
        dfs(root.right, root, depth+1, x, y);
    }

}

//In this approach, we use DFS traversal, but we do not track the parents for each node, but at each node, before traversing to the chidren, we check if both children have values as x and y
//If they are, then we can make the flag false and we include the conditional recursion so that the recursion stops without going for the children.
//Time Complexity: O(n)
//Space Complexity: O(h), where h is the maximum depth of the tree from root node to the leaf node.
class Solution3 {
    int x_depth;
    int y_depth;
    boolean flag;
    public boolean isCousins(TreeNode root, int x, int y) {
        flag = true;
        dfs(root, 0, x, y);
        if(!flag) return false;
        return x_depth == y_depth;
    }

    private void dfs(TreeNode root, int depth, int x, int y){
        //base
        if(root == null) return;

        //logic
        if(root.val == x){
            x_depth = depth;
        }

        if(root.val == y){
            y_depth = depth;
        }
        if(root.left != null && root.right != null){
            if(root.left.val == x && root.right.val == y){
                flag = false;
            }
            if(root.left.val == y && root.right.val == x){
                flag = false;
            }
        }        

        if(flag){
            dfs(root.left, depth+1, x, y);
        }
        if(flag){
            dfs(root.right, depth+1, x, y);
        }
        
    }

}
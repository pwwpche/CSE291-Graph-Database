package Query.Entities;

import Query.Plan.Plan;

/**
 * Created by liuche on 6/4/17.
 */
public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public Plan value;

    public TreeNode(Plan plan){
        this.value = plan;
        this.left = null;
        this.right = null;
    }
}

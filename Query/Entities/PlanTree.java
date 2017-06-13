//package Query.Entities;
//
//import Query.Plan.Plan;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by liuche on 6/4/17.
// */
//public class PlanTree {
//    public TreeNode root;
//
//    public List<Plan> plans;
//
//    public PlanTree(){}
//
//    public PlanTree(PlanTree other){
//        this.root = copy(other.root);
//    }
//
//    public void copyAll(PlanTree other){
//        this.root = copy(other.root);
//    }
//
//    private TreeNode copy(TreeNode other){
//        if(other == null){
//            return null;
//        }
//        TreeNode node = new TreeNode(other.value);
//        node.left = copy(other.left);
//        node.right = copy(other.right);
//        return node;
//    }
//
//
//
//    private void ListDFS(TreeNode cur, List<Plan> nodes){
//        if(cur == null){
//            return;
//        }
//        ListDFS(cur.left, nodes);
//        ListDFS(cur.right, nodes);
//        nodes.add(cur.value);
//    }
//
//
//    public static PlanTree Combine(PlanTree left, PlanTree right, Plan plan){
//        PlanTree res = new PlanTree();
//        res.root = new TreeNode(plan);
//        res.root.left = left.root;
//        res.root.right = right.root;
//        return res;
//    }
//
//    public void add(Plan plan){
//        TreeNode node = new TreeNode(plan);
//        node.left = root;
//        root = node;
//    }
//
//
//    public List<Plan> toList(){
//        List<Plan> list = new ArrayList<>();
//        ListDFS(root, list);
//        return list;
//    }
//}


package Query.Entities;

import Query.Plan.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 6/4/17.
 */
public class PlanTree {

    public List<Plan> plans = new ArrayList<>();

    public PlanTree(){}

    public PlanTree(PlanTree other){
        this.plans.addAll(other.plans);
    }

    public void copyAll(PlanTree other){
        this.plans.addAll(other.plans);
    }


    public static PlanTree Combine(PlanTree left, PlanTree right, Plan plan){
        PlanTree res = new PlanTree();
        res.plans.addAll(left.plans);
        res.plans.addAll(right.plans);
        res.plans.add(plan);
        return res;
    }

    public void add(Plan plan){
        plans.add(plan);
    }
    public List<Plan> toList(){
        return plans;
    }
}

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Emperor sunQuan = new Emperor("Sun Quan", "Emperor", "Cavalry", 96, 98, 72, 77, 95);
        ChiefOfMilitary chiefOfMilitary = new ChiefOfMilitary("Zhou Yu", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        ChiefOfManagement chiefOfManagement = new ChiefOfManagement("Zhang Zhao", "Chief of Management", "Archer", 22, 80, 89, 99, 60);

        sunQuan.addChild(chiefOfMilitary);
        sunQuan.addChild(chiefOfManagement);

        General xuSheng = new General("Xu Sheng", "General", "Archer", 90, 78, 72, 40, 94);
        General zhuGeJin = new General("Zhu Ge Jin", "General", "Archer", 63, 61, 88, 82, 71);
        General luSu = new General("Lu Su", "General", "Infantry", 43, 87, 84, 88, 53);
        General taiShiCi = new General("Tai Shi Ci", "General", "Cavalry", 96, 81, 43, 33, 97);
        General xiaoQiao = new General("Xiao Qiao", "General", "Infantry", 42, 52, 89, 77, 34);
        General daQiao = new General("Da Qiao", "General", "Cavalry", 39, 62, 90, 62, 41);
        General zhouTai = new General("Zhou Tai", "General", "Infantry", 92, 89, 72, 43, 99);
        General ganNing = new General("Gan Ning", "General", "Archer", 98, 92, 45, 23, 97);
        General luMeng = new General("Lu Meng", "General", "Cavalry", 70, 77, 93, 83, 88);
        General huangGai = new General("Huang Gai", "General", "Infantry", 83, 98, 72, 42, 89);

        General[] generals = {xuSheng, zhuGeJin, luSu, taiShiCi, xiaoQiao, daQiao, zhouTai, ganNing, luMeng, huangGai};
        sunQuan.assignGeneralsToDepartments(generals);

        // Accessing the hierarchy
        System.out.println(sunQuan.getName());
        List<WuKingdomNode> children = sunQuan.getChildren();
        for (WuKingdomNode child : children) {
            System.out.println("-- " + child.getName());
            if (child instanceof ChiefOfMilitary) {
                List<WuKingdomNode> grandchildren = child.getChildren();
                for (WuKingdomNode grandchild : grandchildren) {
                    if (grandchild instanceof General) {
                        System.out.println("---- " + grandchild.getName());
                    }
                }
            } else if (child instanceof ChiefOfManagement) {
                List<WuKingdomNode> grandchildren = child.getChildren();
                for (WuKingdomNode grandchild : grandchildren) {
                    if (grandchild instanceof General) {
                        System.out.println("---- " + grandchild.getName());
                    }
                }
            }
        }
    }
}

package cn.pomit.springwork.netty.entity;
public class Attack {

    public static void main(String[] args) {
/*
        Ultraman ultraman =  new Ultraman("迪迦奥特曼",1000);
        Monster monster1 = new Monster("食人魔甲",200);
        Monster monster2 = new Monster("食人魔乙",200);
        Monster monster3= new Monster("食人魔丙",200);
        Monster monster4 = new Monster("食人魔王",500);
        Monster[] m = {monster1,monster2,monster3,monster4};

        System.out.println(ultraman.getName()+"---"+ultraman.getHp());
        for(int i = 0;i<=3;i++)
        {
            System.out.println(m[i].getName()+"---"+m[i].getHp());
        }

        int n = 1;
        while (true) {

            System.out.println(ultraman.getName()+"血量是:"+ultraman.getHp());
            for(int mons=0;mons<=3;mons++)
            {
                System.out.println(m[mons].getName()+"血量是:"+m[mons].getHp());
            }

            System.out.println("=============第"+n+"回合=============");

            if(ultraman.getHp() == 0)
            {
                System.out.println("凹凸曼血量为0,小怪兽胜利");
                break;
            }else if((m[0].getHp()+m[1].getHp()+m[2].getHp()+m[3].getHp())==0)
            {
                System.out.println("所有的小怪兽都死了,凹凸曼胜利");
                break;
            }

            int order = (int) (Math.random() * 2); //区间是[0,1);
            if (order == 1) {
                int Ult = (int) (Math.random() * 10);
                if (Ult == 0 || Ult == 1 || Ult == 2) {
                    System.out.println(ultraman.getName()+"使用了魔法攻击,攻击所有怪兽");
                    ultraman.MagicAttack(m);
                } else if (Ult >= 3 && Ult <= 8) {

                    int i = (int) (Math.random() * 4);
                    System.out.println(ultraman.getName()+"使用了普通攻击,攻击了"+m[i].getName());
                    ultraman.Attack(m[i]);
                } else {
                    int j = (int) (Math.random() * 4);
                    System.out.println(ultraman.getName()+"使用了必杀攻击,攻击了"+m[j].getName());
                    ultraman.HugeAttack(m[j]);
                }

                for (int mon = 0; mon <= 3; mon++) {

                    if(m[mon].getHp() == 0)
                    {
                        mon++;
                    }else{
                        System.out.println(m[mon].getName()+"使用了普通攻击");
                        m[mon].Attack(ultraman);
                    }
                }

            } else {

                for (int mon = 0; mon <= 3; mon++) {

                    if(m[mon].getHp() == 0)
                    {
                        mon++;
                    }else{
                        System.out.println(m[mon].getName()+"使用了普通攻击");
                        m[mon].Attack(ultraman);
                    }
                }

                int Ult = (int) (Math.random() * 10);
                if (Ult == 0 || Ult == 1 || Ult == 2) {
                    System.out.println(ultraman.getName()+"使用了魔法攻击,攻击所有怪兽");
                    ultraman.MagicAttack(m);
                } else if (Ult >= 3 && Ult <= 8) {
                    int i = (int) (Math.random() * 4);
                    System.out.println(ultraman.getName()+"使用了普通攻击,攻击了"+m[i].getName());
                    ultraman.Attack(m[i]);
                } else {
                    int j = (int) (Math.random() * 4);
                    System.out.println(ultraman.getName()+"使用了必杀攻击,攻击了"+m[j].getName());
                    ultraman.HugeAttack(m[j]);
                }

            }

            for(int change = 0;change <= 3;change++)
            {
                if(m[change].getHp() < 0)
                {
                    m[change].setHp(0);
                }
            }

            n++;

        }


    }

 */
    }
}



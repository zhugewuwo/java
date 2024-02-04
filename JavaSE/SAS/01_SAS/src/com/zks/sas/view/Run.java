package com.zks.sas.view;


import javax.swing.*;

public class Run {
    public static void main(String[] args) {
        try {
            // 设置观感
            //UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel());
            //UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceCremeCoffeeLookAndFeel());
            //UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel());
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
         new LoginFrame().setVisible(true);
    }
}

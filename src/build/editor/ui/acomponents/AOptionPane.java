package build.editor.ui.acomponents;

import build.editor.J3DBuild;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class AOptionPane {

    public static int answer = 0;

    private static JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent) parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }

    public static void showMessage(String title, String message) {
        AButton okBtn = new AButton("OK");
        okBtn.setPreferredSize(new Dimension(56, 22));
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane(okBtn);
                pane.setValue(1);
            }
        });

        JOptionPane.showOptionDialog(J3DBuild.instance, getMessagePanel(message), title, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION, null, new AButton[]{okBtn}, 0);
    }

    private static APanel getMessagePanel(String message) {
        APanel panel = new APanel();
        ALabel label = new ALabel(message);
        panel.add(label);

        return panel;
    }

    public static void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(J3DBuild.instance, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarningMessage(String title, String message) {
        JOptionPane.showMessageDialog(J3DBuild.instance, message, title, JOptionPane.WARNING_MESSAGE);
    }

    public static void showInfoMessage(String title, String message) {
        JOptionPane.showMessageDialog(J3DBuild.instance, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean getQuestion(String title, String message) {
        answer = 0;

        AButton yesBtn = new AButton("Yes");
        yesBtn.setPreferredSize(new Dimension(56, 22));
        yesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane(yesBtn);
                answer = 1;
                pane.setValue(1);
            }
        });

        AButton noBtn = new AButton("No");
        noBtn.setPreferredSize(new Dimension(56, 22));
        noBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane(noBtn);
                pane.setValue(1);
            }
        });

        JOptionPane.showOptionDialog(J3DBuild.instance, getMessagePanel(message), title, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION, null, new AButton[]{yesBtn, noBtn}, 0);

        return (answer == 1);
    }

    /**
     * returns 1 if answer is yes, 0 if answer is no, 2 if answer is cancel
     *
     * @param title
     * @param message
     * @return
     */
    public static int getQuestionCancel(String title, String message) {
        answer = 0;

        AButton yesBtn = new AButton("Yes");
        yesBtn.setPreferredSize(new Dimension(56, 22));
        yesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane(yesBtn);
                answer = 1;
                pane.setValue(1);
            }
        });

        AButton noBtn = new AButton("No");
        noBtn.setPreferredSize(new Dimension(56, 22));
        noBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane(noBtn);
                answer = 0;
                pane.setValue(1);
            }
        });

        AButton cancelBtn = new AButton("Cancel");
        cancelBtn.setPreferredSize(new Dimension(56, 22));
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane(cancelBtn);
                answer = 2;
                pane.setValue(1);
            }
        });

        JOptionPane.showOptionDialog(J3DBuild.instance, getMessagePanel(message), title, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_OPTION, null, new AButton[]{yesBtn, noBtn, cancelBtn}, 0);

        return answer;
    }
}

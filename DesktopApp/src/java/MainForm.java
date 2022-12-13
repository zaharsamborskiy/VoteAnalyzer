import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm
{
    private JPanel mainPanel;
    private JTextArea lastNameArea;
    private JTextArea fullNameArea;
    private JButton collapseButton;
    private JLabel surNameLabel;
    private JLabel nameLabel;
    private JLabel lastNameLabel;
    private JLabel fullnameLabel;
    private JTextArea surNameArea;
    private JTextArea nameArea;
    private String regex = "^[A-Za-z,А-Яа-я]*$";

    public JPanel getMainPanel()
    {
        fullnameLabel.setVisible(false);
        fullNameArea.setVisible(false);
        return mainPanel;
    }

    public MainForm()
    {
        collapseButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (collapseButton.getText().equals("Collapse"))
                {
                    if (!lastNameArea.getText().matches(regex))
                    {
                        JOptionPane.showMessageDialog(mainPanel, "Если вы заполняете поле \"Oтчество\" \n то вы должны соблюдать правила заполнения");
                        return;
                    }
                    if (checkEntry(surNameArea.getText(), nameArea.getText()))
                    {
                        errorMessage();
                    }
                    else if ((refactor(surNameArea.getText()).equals("") || refactor(nameArea.getText()).equals("")))
                    {
                        errorMessage2();
                    }
                    else
                    {
                        collapseButton.setText("Expand");
                        fullNameArea.setText(createFullName());
                        setPanelVisible(false);
                        setFullNameVisible(true);
                    }
                }
                else
                {
                    String[] fio = fullNameArea.getText().split("\\s+");
                    if (fio.length == 1)
                    {
                        errorMessage3();
                        return;
                    }
                    if (checkEntry(fio[0], fio[1]))
                    {
                        errorMessage();
                    }
                    else if (refactor(fio[0]).equals("") || refactor(fio[1]).equals(""))
                    {

                        errorMessage2();
                    }
                    else if (fio.length == 2)
                    {
                        surNameArea.setText(fio[0]);
                        nameArea.setText(fio[1]);
                        lastNameArea.setText("");
                    }
                    else if (fio.length > 2)
                    {
                        clearTextAreas();
                        surNameArea.setText(fio[0]);
                        nameArea.setText(fio[1]);
                        lastNameArea.setText(fio[2]);
                        if ((refactor(fio[2]).equals("")) || refactor(fio[1]).equals("") || refactor(fio[0]).equals(""))
                        {
                            clearTextAreas();
                            errorMessage2();
                        }
                    }
                        collapseButton.setText("Collapse");
                        setFullNameVisible(false);
                        setPanelVisible(true);
                    }
                }
        });
    }

    public boolean checkEntry(String surnameField, String nameField)
    {
        return (surnameField.isEmpty() || nameField.isEmpty());
    }

    public void errorMessage()
    {
        clearTextAreas();
        JOptionPane.showMessageDialog(mainPanel, "Введите фамилию и имя!", "Ошибка!", JOptionPane.PLAIN_MESSAGE);
    }

    public void errorMessage2()
    {
        clearTextAreas();
        JOptionPane.
                showMessageDialog(mainPanel,
                        "Данные не могут содержать запятые, точки, цифры и т.д.\nВы будете возвращены к основной форме",
                        "Ошибка!",
                        JOptionPane.PLAIN_MESSAGE);
    }

    public void errorMessage3()
    {

        JOptionPane.showMessageDialog(mainPanel,
                "Некорректный ввод данных!\nДанные необходимо ввести через пробел!",
                "Ошибка!",
                JOptionPane.PLAIN_MESSAGE);
    }

    private String refactor(String refactor)
    {
        String newString = refactor.trim();
        if (newString.matches(regex)) {
            return newString;
        }
        return "";
    }

    private void setPanelVisible(boolean setTrueOrFalse)
    {
        surNameArea.setVisible(setTrueOrFalse);
        nameArea.setVisible(setTrueOrFalse);
        lastNameArea.setVisible(setTrueOrFalse);
        surNameLabel.setVisible(setTrueOrFalse);
        nameLabel.setVisible(setTrueOrFalse);
        lastNameLabel.setVisible(setTrueOrFalse);
    }

    private void setFullNameVisible(boolean setTrueOrFalse)
    {
        fullNameArea.setVisible(setTrueOrFalse);
        fullnameLabel.setVisible(setTrueOrFalse);
    }

    private String createFullName()
    {
        return surNameArea.getText() + " "
                + nameArea.getText() + " "
                + lastNameArea.getText();
    }
        private void clearTextAreas()
        {
            surNameArea.setText("");
            nameArea.setText("");
            lastNameArea.setText("");
            fullNameArea.setText("");
        }
    }

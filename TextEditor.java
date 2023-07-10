import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {
    // Declaring the properties of textEditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;
    JTextArea textArea;

    TextEditor(){
        //Initialize a frame
        frame = new JFrame();

        //Initialize a menu-bar;
        menuBar = new JMenuBar();

        //Initialize a textArea
        textArea = new JTextArea();

        //Initialize a menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize file menuItem
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //Add ActionListener to the File menu item
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add the menu item to the file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize edit numItem
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        //Add ActionListener to the Edit menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Add the menu item to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menu to the menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Create content panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add textArea to the panel
        panel.add(textArea, BorderLayout.CENTER);

        //Create scroll Pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add ScrollPanel to frame
        panel.add(scrollPane);
        //Add Panel to frame
        frame.add(panel);
        //set the menu-bar
        frame.setJMenuBar(menuBar);
        //set the dimension of the frame
        frame.setBounds(0, 0, 500, 500);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //Perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //Perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //Perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //Perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //Perform close operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //If we have clicked on Open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file = fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "";
                    String output = "";
                    //Read content of file line by line
                    while((intermediate= bufferedReader.readLine())!=null){
                        output = output+intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");

            //Choose Option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on solve button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Create a new file with chooser directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize Buffered Writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write content of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
       TextEditor textEditor = new TextEditor();
    }
}
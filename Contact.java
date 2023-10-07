/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class AddressBookUI extends JFrame
    {private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField emailAddressField;
    private JTextArea displayArea;
    private List<Contact> contacts;
    public AddressBookUI()
        {contacts = new ArrayList<>();
        JLabel nameLabel = new JLabel("Name:");
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JLabel emailAddressLabel = new JLabel("Email Address:");
        nameField = new JTextField(20);
        phoneNumberField = new JTextField(20);
        emailAddressField = new JTextField(20);
        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener()
            {@Override
            public void actionPerformed(ActionEvent e)
                {String name = nameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String emailAddress = emailAddressField.getText();
                if (!isDuplicateContact(name))
                    {if (isValidPhoneNumber(phoneNumber) && isValidEmailAddress(emailAddress) && !isDuplicateContact(name))
                        {Contact contact = new Contact(name, phoneNumber, emailAddress);
                        contacts.add(contact);
                        displayContacts();
                        }
                    else
                        {JOptionPane.showMessageDialog(AddressBookUI.this, "Invalid phone number or email address, or duplicate contact.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                else
                    {JOptionPane.showMessageDialog(AddressBookUI.this, "Contact with the same name already exists.", "Duplicate Contact", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        JButton removeButton = new JButton("Remove Contact");
        removeButton.addActionListener(new ActionListener()
            {@Override
            public void actionPerformed(ActionEvent e)
                {String nameToRemove = JOptionPane.showInputDialog("Enter name to remove:");
                removeContactByName(nameToRemove);
                displayContacts();
                }
            });
        JButton searchButton = new JButton("Search Contact");
        searchButton.addActionListener(new ActionListener()
            {@Override
            public void actionPerformed(ActionEvent e)
                {String nameToSearch = JOptionPane.showInputDialog("Enter name to search:");
                searchContactByName(nameToSearch);
                }
            });
        JButton displayAllButton = new JButton("Display All Contacts");
        displayAllButton.addActionListener(new ActionListener()
            {@Override
            public void actionPerformed(ActionEvent e)
                {displayArea.setVisible(true);
                displayContacts();
                }
            });
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        displayArea.setVisible(false);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberField);
        inputPanel.add(emailAddressLabel);
        inputPanel.add(emailAddressField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayAllButton);
        JPanel displayPanel = new JPanel();
        displayPanel.add(new JScrollPane(displayArea));
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.SOUTH);
        setTitle("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        }
    private boolean isDuplicateContact(String name)
        {for (Contact contact : contacts)
            {if (contact.getName().equalsIgnoreCase(name))
                {return true;
                }
            }
        return false;
        }
    private boolean isValidPhoneNumber(String phoneNumber)
        {return phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}|\\d{10}");
        }
    private boolean isValidEmailAddress(String emailAddress)
        {return emailAddress.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
        }
    private void removeContactByName(String name)
        {contacts.removeIf(contact -> contact.getName().equalsIgnoreCase(name));
        }
    private void searchContactByName(String name)
        {boolean found = false;
        for (Contact contact : contacts)
            {if (contact.getName().equalsIgnoreCase(name))
                {JOptionPane.showMessageDialog(this, contact.toString(), "Contact Details", JOptionPane.INFORMATION_MESSAGE);
                found = true;
                break;
                }
            }
        if (!found)
            {JOptionPane.showMessageDialog(this, "Contact not found.", "Search Result", JOptionPane.WARNING_MESSAGE);
            }
        }
    private void displayContacts()
        {displayArea.setText("");
        for (Contact contact : contacts)
            {displayArea.append(contact.toString() + "\n\n");
            }
        }
    public static void main(String[] args)
        {SwingUtilities.invokeLater(new Runnable()
            {@Override
            public void run()
                {new AddressBookUI().setVisible(true);
                }
            });
        }
    }
public class Contact
    {private String name;
    private String phoneNumber;
    private String emailAddress;
    public Contact(String name, String phoneNumber, String emailAddress)
        {this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        }
    public String getName()
        {return name;
        }
    public String getPhoneNumber()
        {return phoneNumber;
        }
    public String getEmailAddress()
        {return emailAddress;
        }
    public void setName(String name)
        {this.name = name;
        }
    public void setPhoneNumber(String phoneNumber)
        {this.phoneNumber = phoneNumber;
        }
    public void setEmailAddress(String emailAddress)
        {this.emailAddress = emailAddress;
        }
    public String toString()
        {return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
        }
    }

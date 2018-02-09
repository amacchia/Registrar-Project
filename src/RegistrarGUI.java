import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class RegistrarGUI extends JFrame{

	public RegistrarGUI(Term term, Student student) {
		super("Banner self service for " + student.getLastName());
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(1200, 120));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addComponents(term, student);
		pack();
		setVisible(true);
	}

	// Adds the components to the frame
	private void addComponents(Term term, Student student) {

		// Create text area for list of sections in a department
		JTextPane sectionTextPane = new JTextPane();
		add(sectionTextPane, BorderLayout.CENTER);

		// Create label for matching sections
		JLabel feedbackLabel = new JLabel("Matching Sections");
		feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(feedbackLabel, BorderLayout.SOUTH);

		// Create JComboBox for selection of department
		JPanel northPanel = new JPanel();
		JComboBox<Department> departmentComboBox = new JComboBox<>(Department.values());
		departmentComboBox.insertItemAt(null, 0);	// Add null option to combo box
		departmentComboBox.setSelectedIndex(0);		// Set initial selection to null
		departmentComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Department dept = (Department) departmentComboBox.getSelectedItem();
				if (dept == null) {
					departmentComboBox.setSelectedIndex(0);
					departmentComboBox.hidePopup();
					sectionTextPane.removeAll();
					feedbackLabel.setText("Matching Sections");
					getContentPane().revalidate();
					getContentPane().repaint();
				} else {
					populateTextArea(dept, term, sectionTextPane, student, feedbackLabel);
				}

			}
		});

		JLabel selectDeptLabel = new JLabel("Select a Department ");
		northPanel.add(selectDeptLabel);
		northPanel.add(departmentComboBox);
		add(northPanel, BorderLayout.NORTH);
	}

	// Populates the text area component of the frame
	private void populateTextArea(Department dept, Term term, JTextPane sectionTextPane, 
			Student student, JLabel feedbackLabel) {
		// Clear text pane of previous components
		sectionTextPane.removeAll();
		sectionTextPane.revalidate();
		sectionTextPane.repaint();

		// Set the layout manager for text pane
		sectionTextPane.setLayout(new BoxLayout(sectionTextPane, BoxLayout.Y_AXIS));
		sectionTextPane.setBorder(null);

		// Create Title Panel to add to text area
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(1, 5));
		titlePanel.setBackground(Color.PINK);
		// Create Titles to add to title panel
		JLabel crnTitle = new JLabel("Click CRN to add or drop course");
		crnTitle.setForeground(Color.BLUE);
		crnTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel courseNumberTitle = new JLabel("Course Number");
		courseNumberTitle.setForeground(Color.BLUE);
		courseNumberTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel courseTitle = new JLabel("Title");
		courseTitle.setForeground(Color.BLUE);
		courseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel instructorTitle = new JLabel("Instructor");
		instructorTitle.setForeground(Color.BLUE);
		instructorTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel scheduleTitle = new JLabel("Schedule");
		scheduleTitle.setForeground(Color.BLUE);
		scheduleTitle.setHorizontalAlignment(SwingConstants.CENTER);

		// Add titles to title panel with glue
		titlePanel.add(crnTitle);
		titlePanel.add(courseNumberTitle);
		titlePanel.add(courseTitle);
		titlePanel.add(instructorTitle);
		titlePanel.add(scheduleTitle);

		// Add panel to text area
		sectionTextPane.add(titlePanel);

		// Keep count of matching sections
		int matchCount = 0;
		for (Section section : term.getSections()) {
			if (dept.equals(section.getCourse().getDepartment())) {
				matchCount++;
				// Create a panel for each course in the selected department
				JPanel coursePanel = new JPanel();
				coursePanel.setLayout(new GridLayout(1, 5));
				coursePanel.setBackground(Color.LIGHT_GRAY);

				// Create Panel for the button so the button does not fill its entire grid box
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
				buttonPanel.setBackground(Color.LIGHT_GRAY);
				// Create Button for adding and dropping a section
				JButton crnButton = new JButton(String.valueOf(section.getCRN()));
				crnButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// Drop the section if the student is already registered for the section
						if (student.getCurrentSections().containsKey(Integer.valueOf(crnButton.getText()))) {
							student.drop(section);
						} else if (section instanceof Hybrid) { // If the section is hybrid validate registration
							Hybrid hybridSection = (Hybrid) section;
							int choice = hybridSection.validateChoice(getContentPane());
							if (choice == JOptionPane.YES_OPTION) {
								student.add(hybridSection);
							}
						} else  if (section instanceof Online){ // If the section is online validate registration
							Online onlineSection = (Online) section;
							int choice = onlineSection.validateChoice(getContentPane());
							if (choice == JOptionPane.YES_OPTION) {
								student.add(onlineSection);
							}
						} else { // Add the section to the student
							student.add(section);
						}
						// Show feedback in feedback label
						setFeedbackLabel(feedbackLabel, student);
					}
				});
				buttonPanel.add(crnButton);
				
				// Create labels for course info
				JLabel courseNumberLabel = new JLabel(section.getCourse().getCourseNumber());
				courseNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel courseLabel = new JLabel(section.getCourse().getTitle());
				courseLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel instructorLabel = new JLabel(section.getInstructor().getLastName());
				instructorLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel scheduleLabel = new JLabel();
				scheduleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				
				// Set the schedule label based on the type of section
				if (section instanceof Traditional) {
					Traditional traditionalSection = (Traditional) section;
					scheduleLabel.setText(traditionalSection.getSchedule());
				} else if (section instanceof Hybrid) {
					Hybrid hybridSection = (Hybrid) section;
					scheduleLabel.setText(hybridSection.getSchedule());
				} else  if (section instanceof Online){
					Online onlineSection = (Online) section;
					scheduleLabel.setText(onlineSection.getSchedule());
				}

				// Add the labels to the panel
				coursePanel.add(buttonPanel);
				coursePanel.add(courseNumberLabel);
				coursePanel.add(courseLabel);
				coursePanel.add(instructorLabel);
				coursePanel.add(scheduleLabel);


				// Add the panel to the textPane
				sectionTextPane.add(coursePanel);
				sectionTextPane.setPreferredSize(new Dimension(1475, 400));				
			}
		}
		// Display feedback of matching sections
		feedbackLabel.setText(matchCount + " matching sections of " + dept.toString().toLowerCase() + " found.");
		pack();
	}

	// Set the text of the feedback label
	private void setFeedbackLabel(JLabel feedbackLabel, Student student) {
		// Clear feedback label
		feedbackLabel.setText("");
		Iterator<Section> sectionsIterator = student.getCurrentSections().values().iterator();
		while (sectionsIterator.hasNext()) {
			Section currentSection = sectionsIterator.next();
			feedbackLabel.setText(feedbackLabel.getText() + currentSection.getCRN() + " " + currentSection.getCourse().getTitle() + ", "   );
		}
	}	
}
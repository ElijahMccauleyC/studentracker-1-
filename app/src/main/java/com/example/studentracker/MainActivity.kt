package com.example.studentracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var studentListTextView: TextView
    private lateinit var addStudentButton: Button
    private lateinit var studentNameEditText: EditText // Declare EditText

    private val studentList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentListTextView = findViewById(R.id.studentListTextView)
        addStudentButton = findViewById(R.id.addStudentButton)
        studentNameEditText = findViewById(R.id.studentNameEditText) // Initialize EditText

        addStudentButton.setOnClickListener {
            addStudent()
        }
    }

    private fun addStudent() {
        if (studentList.size >= 10) {
            // Maximum students reached, handle accordingly (show message, disable button, etc.)
            return
        }

        // Get student name from EditText
        val newStudentName = studentNameEditText.text.toString().trim()

        // Check if the name starts with a capital letter
        if (!newStudentName.matches(Regex("^[A-Z].*"))) {
            // Display error or inform the user that the name should start with a capital letter
            studentNameEditText.error = "Name must start with a capital letter"
            return
        }

        // Check if the student already exists
        if (studentList.contains(newStudentName)) {
            // Optionally handle the case where the student is already in the list
            return
        }

        // Name is valid, add to the list
        studentList.add(newStudentName)

        // Sort studentList alphabetically
        studentList.sort()

        // Update the student list display
        updateStudentList()

        // Clear EditText after adding student
        studentNameEditText.text.clear()
    }

    private fun removeStudent(studentName: String) {
        // Remove student from the list
        studentList.remove(studentName)

        // Update the student list display
        updateStudentList()
    }

    private fun updateStudentList() {
        // Build the list string
        val sb = StringBuilder()
        for (student in studentList) {
            sb.append(student).append("\n")
        }
        studentListTextView.text = sb.toString()

        // Set click listener for student names
        studentListTextView.setOnClickListener { view ->
            val clickedText = (view as TextView).text.toString()

            // Extract student name from clickedText
            val studentName = clickedText.substringBefore("\n")

            // Call removeStudent() function
            removeStudent(studentName)
        }
    }
}

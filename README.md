# WEB Technologies 1 - Assignment 4

Name: Nurassyl Zhumagul

Group: IT-2501

Open `index.html` in a browser. An internet connection is needed to load Bootstrap from the CDN.

## Task 0 - Simple Contact Form

I created a basic contact form with Name, Email, Message, and a Submit button. Each field has a matching label, and this task uses no Bootstrap classes or JavaScript.

Screenshot placeholder: Add a screenshot of Task 0 here.

## Task 1 - HTML5 Validation

I added required fields and maximum lengths for the name and message. The email field uses `type="email"`, so the browser checks the email format without custom JavaScript.

Screenshot placeholder: Add a screenshot of Task 1 here.

## Task 2 - Bootstrap Form Styling

I styled the contact form with Bootstrap classes, placeholders, and email helper text. The Bootstrap Grid puts Name and Email beside each other on medium and larger screens and stacks them on small screens.

Screenshot placeholder: Add a screenshot of Task 2 here.

## Task 3 - Bootstrap Validation

I used `is-valid` and `is-invalid` on two example fields. These classes show fixed validation styles, while `valid-feedback` and `invalid-feedback` show messages; they do not check the values.

Screenshot placeholder: Add a screenshot of Task 3 here.

## Task 4 - Registration Form

I created a registration form with first name, last name, email, password, confirm password, gender, hobbies, country, Submit, and Reset. One submit event checks password matching and HTML5 rules, blocks invalid submission, and adds Bootstrap validation styles. Reset restores the field values; reload the page to clear the validation styles.

Screenshot placeholder: Add a screenshot of Task 4 here.

## Summary

This project demonstrates HTML forms, HTML5 validation, Bootstrap styling, and simple password matching.

## Simple Defense Notes

- Each task has its own section and a matching comment in the source.
- `label for` matches an input's `id` to connect the label to that field.
- `action="#task4"` submits back to this page at Task 4. The other forms use their own section.
- `method="get"` puts named form values in the page URL. There is no backend to process them.
- Password fields have no `name`, so their values are not added to the URL.
- `novalidate` disables automatic validation popups in Task 4. `checkValidity()` still checks the HTML rules.
- Password matching runs when Submit is pressed. HTML alone cannot compare two password fields.
- `setCustomValidity("Passwords do not match")` makes confirmation invalid; `setCustomValidity("")` clears that custom error.
- `event.preventDefault()` stops an invalid submission. `was-validated` turns on Bootstrap validation styles.
- A valid submission reloads this page. There is no success message, data storage, or account creation.

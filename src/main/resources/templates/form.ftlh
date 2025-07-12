<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <#if employee??>
    <title>Edit Employee</title>
  <#else>
    <title>Add Employee</title>
  </#if>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
  <div id="form-container">
    <#if employee??>
      <h2>Edit Employee</h2>
    <#else>
      <h2>Add Employee</h2>
    </#if>

    <form method="post" action="/submit">
      <input type="hidden" name="id" value="${employee.id!}" />

      <label>First Name:</label>
      <input type="text" name="firstName" value="${employee.firstName!}" class = "searchBar" required />

      <label>Last Name:</label>
      <input type="text" name="lastName" value="${employee.lastName!}" class = "searchBar" required />

      <label>Email:</label>
      <input type="email" name="email" value="${employee.email!}" class = "searchBar" required />

      <label>Department:</label>
      <input type="text" name="department" value="${employee.department!}" class = "searchBar" required />

      <label>Role:</label>
      <input type="text" name="role" value="${employee.role!}" class = "searchBar" required />

      <div class="form-buttons">
        <button type="submit">Save</button>
        <a href="/" class="cancel-btn">Cancel</a>
      </div>
    </form>
  </div>
</body>
</html>

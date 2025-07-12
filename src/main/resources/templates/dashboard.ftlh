<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Employee Directory</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="/css/style.css" />
</head>
<body>
  <div id="app" class = "bg-container">
    <header class = "header-bg">
      <h1>Employee Directory</h1>

      <form method="get" action="/">
        <input
          type="text"
          name="query"
          id="searchBar"
          placeholder="Search by name or email..."
          value="${query!''}"
          class = "searchBar"
        />
        
        <select name="department" class = "select-dept">
          <option value="">All Departments</option>
          <#list departments as dept>
            <option value="${dept}" <#if dept == selectedDepartment!''>selected</#if>>${dept}</option>
          </#list>
        </select>
        <select name="role" class = "select-dept">
          <option value="">All Roles</option>
          <#list roles as r>
            <option value="${r}" <#if r == selectedRole!''>selected</#if>>${r}</option>
          </#list>
        </select>

        <button type="submit">Filter</button>
        <button onclick="location.href='/'" type="button">Reset</button>
        <button onclick="location.href='/form'" type="button">Add Employee</button>
      </form>
    </header>

    <main>
      <div id="employeeList" class = "employee-list">
        <#list employees as employee>
          <div class="employee-card">
            <h3>${employee.firstName} ${employee.lastName}</h3>
            <p><strong>ID:</strong> ${employee.id}</p>
            <p><strong>Email:</strong> ${employee.email}</p>
            <p><strong>Department:</strong> ${employee.department}</p>
            <p><strong>Role:</strong> ${employee.role}</p>
            <button onclick="location.href='/form?id=${employee.id}'">Edit</button>
            <button onclick="location.href='/delete?id=${employee.id}'">Delete</button>
          </div>
        </#list>
      </div>
    </main>
  </div>
</body>
</html>

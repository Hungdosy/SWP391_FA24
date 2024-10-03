<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Project List</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h2 class="mt-4">Project List</h2>

            <!-- Table displaying project details -->
            <table class="table table-bordered table-hover mt-4">
                <thead>
                    <tr>
                        <th>Project Name</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- JSTL foreach loop to iterate through the projectList -->
                    <c:forEach var="project" items="${projectList}">
                        <tr>
                            <td>${project.title}</td>
                            <td>${project.startDate}</td>
                            <td>${project.endDate}</td>
                            <td>
                                <a href="project-detail?id=${project.projectId}" class="btn btn-info">Details</a>
                            </td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>

            <!-- Button to create a new project (optional, depending on your project requirements) -->
            <a href="create-project.jsp" class="btn btn-primary mt-4">Create New Project</a>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

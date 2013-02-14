<?php
header('Content-Type: application/json');

if (isset($_GET['EmployeeID'])) {
  $employeeId = $_GET['EmployeeID'];
} else {
  $employeeId = null;
}

$db = new PDO('sqlite:../data/sample.db');

$sql = 'SELECT m.EmployeeID, m.FirstName, m.LastName, m.EmployeeID, '
  . '(SELECT COUNT(*) FROM Employees x WHERE x.ReportsTo=m.EmployeeID) as HasEmployees '
  . 'FROM Employees m '
  . 'WHERE ReportsTo is ?';

$statement = $db->prepare($sql);

$statement->execute(array($employeeId));

$data = $statement->fetchAll(PDO::FETCH_ASSOC);

// iterate over data to set computed keys
$employees = array();

foreach ($data as $employee) {
  $employee["FullName"] = $employee["FirstName"] . " " . $employee["LastName"];
  $employee["HasEmployees"] = $employee["HasEmployees"] != 0;
  $employees[] = $employee;
}

echo json_encode($employees);
?>

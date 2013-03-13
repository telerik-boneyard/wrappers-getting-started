<?PHP
require_once 'lib/Kendo/Autoload.php';
include 'header.php';

// ---- Kendo UI DataSource Transport ----

$read = new Kendo\Data\DataSourceTransportRead();
$read->url('/api/employees.php')
  ->contentType('application/json'); 

$transport = new Kendo\Data\DataSourceTransport();
$transport->read($read);

// ---- Kendo UI Hierarchical DataSource ----

$model = new \Kendo\Data\HierarchicalDataSourceSchemaModel();
$model->id("EmployeeID")
  ->hasChildren("HasEmployees");

$schema = new \Kendo\Data\HierarchicalDataSourceSchema();
$schema->model($model);

$dataSource = new \Kendo\Data\HierarchicalDataSource();
$dataSource->transport($transport)
  ->schema($schema);
  
// ---- Kendo UI TreeView ----

$treeview = new \Kendo\UI\TreeView('employee-list');
$treeview->dataSource($dataSource)
  ->dataTextField("FullName");
?>

<h2>Employees</h2>
<p>
A Kendo UI TreeView control with a hierarchical 
data source, lazy-loading the employee hierarchy.
</p>
<?= $treeview->render(); ?>

<?php
  include 'footer.php';
?>

<?PHP
include 'header.php';

$transport = new Kendo\Data\DataSourceTransport();
$read = new Kendo\Data\DataSourceTransportRead();

$read
  ->url('/api/employees.php')
  ->contentType('application/json'); 

$transport
  ->read($read);

$model = new \Kendo\Data\HierarchicalDataSourceSchemaModel();
$model
  ->id("EmployeeID")
  ->hasChildren("HasEmployees");

$schema = new \Kendo\Data\HierarchicalDataSourceSchema();
$schema->model($model);

$dataSource = new \Kendo\Data\HierarchicalDataSource();
$dataSource
  ->transport($transport)
  ->schema($schema);
  
$treeview = new \Kendo\UI\TreeView('employee-list');
$treeview
  ->dataSource($dataSource)
  ->dataTextField("FullName");
?>

<div class="container">
  <div class="row">
    <div class="span12">
      <h2>Employees</h2>
      <?= $treeview->render(); ?>
    </div>
  </div>
</div>

<?php
  include 'footer.php';
?>

<?php
  include 'header.php';
?>

<div class="container">
  <div class="row">
    <div class="span12">
      <h2>Employees</h2>
      
      <kendo:treeView name="employees" dragAndDrop="true" dataTextField="FullName" >
        <kendo:dataSource>
          <kendo:dataSource-transport>
            <kendo:dataSource-transport-read url="api/employees">
            </kendo:dataSource-transport-read>
          </kendo:dataSource-transport>
          <kendo:dataSource-schema>
            <kendo:dataSource-schema-hierarchical-model id="EmployeeID" 
            hasChildren="HasChildren"></kendo:dataSource-schema-hierarchical-model>
          </kendo:dataSource-schema> 
        </kendo:dataSource>
      </kendo:treeView>
    </div>
  </div>
</div>

<?php
  include 'footer.php';
?>

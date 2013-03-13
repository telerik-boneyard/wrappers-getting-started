<?PHP
require_once 'lib/Kendo/Autoload.php';
include 'header.php';

// ---- PHP Objects / Array ----

$obj1 = new stdClass();
$obj1->id = 1;
$obj1->value = "foo";

$obj2 = new stdClass();
$obj2->id = 2;
$obj2->value = "bar";

$obj3 = new stdClass();
$obj3->id = 3;
$obj3->value = "baz";

$myList = array($obj1, $obj2, $obj3);

// ---- Kendo UI DataSource ----

$ds = new \Kendo\Data\DataSource();
$ds->data($myList);

// ---- Kendo UI DropDownList ----

$dropList = new \Kendo\UI\DropDownList('my-list');
$dropList->dataSource($ds)
  ->dataValueField("id")
  ->dataTextField("value");
?>

<h2>A DropDown List</h2>
<p>
A Kendo UI DropDown list with a hard coded list
of items as the data source.
</p>
<?= $dropList->render(); ?>

<?php
  include 'footer.php';
?>


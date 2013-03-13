<?PHP
require_once 'lib/Kendo/AutoLoad.php';
include 'header.php';

$calendar = new \Kendo\UI\Calendar('calendar');
?>

<h2>Calendar Control</h2>
<p>
An example of using the Kendo UI Calendar control.
</p>
<?= $calendar->render(); ?>

<?PHP
include 'footer.php';
?>

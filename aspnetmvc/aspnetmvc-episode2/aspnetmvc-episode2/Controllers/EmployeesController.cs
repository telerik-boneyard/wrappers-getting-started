using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace aspnetmvc_episode2.Controllers
{
    public class EmployeesController : Controller
    {
        readonly Data.NorthwindEntities _context = new Data.NorthwindEntities();

        public ActionResult Index(int? id)
        {
            var employees = _context.Employees
                .Where(e => id.HasValue ? e.ReportsTo == id : e.ReportsTo == null)
                .Select(e => new {
                    id = e.EmployeeID,
                    Name = e.FirstName + " " + e.LastName,
                    hasChildren = e.Employees1.Any()
                });


            return this.Json(employees, JsonRequestBehavior.AllowGet);

        }

    }
}

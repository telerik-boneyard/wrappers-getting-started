using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace aspnetmvc_episode4.Controllers
{
    public class SuppliersController : Controller
    {
        readonly Data.SampleEntities _entities = new Data.SampleEntities();

        public JsonResult Index()
        {
            var suppliers = _entities.Suppliers.Select(s => new Models.Supplier {
                Id = s.SupplierID,
                Name = s.CompanyName
            });

            return this.Json(suppliers, JsonRequestBehavior.AllowGet);
        }

    }
}

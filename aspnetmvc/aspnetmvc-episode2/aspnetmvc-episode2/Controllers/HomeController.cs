using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace aspnetmvc_episode2.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            var things = new List<string>() {
                "Foo", "Bar", "Baz"
            };

            return View(things);
        }
    }
}

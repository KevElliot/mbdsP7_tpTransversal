using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace winform_TpTransversal.Model
{
    public class Login
    {
        public string _id { get; set; }
        public string name { get; set; }
        public string password { get; set; }
        public string email { get; set; }
        public string role { get; set; }
        public string image { get; set; }
        public float jetons { get; set; }
        public Boolean isConnecte = false;

        public Boolean varIsconnecte
        {
            get { return isConnecte; }
            set { isConnecte = value; }
        }

        public Login(string id, string name, string password, string email, string role, string image, float jetons)
        {
            _id = id;
            this.name = name;
            this.password = password;
            this.email = email;
            this.role = role;
            this.image = image;
            this.jetons = jetons;
        }

        public Login()
        {
        }
    }
}

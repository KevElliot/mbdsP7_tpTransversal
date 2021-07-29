using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace winform_TpTransversal.Model
{
    public class DemandeJeton
    {
        public string iduser { get; set; }
        public int jetonsdemande { get; set; }
        public string statut { get; set; }

        public DemandeJeton(string iduser, int jetonsdemande, string statut)
        {
            this.iduser = iduser;
            this.jetonsdemande = jetonsdemande;
            this.statut = statut;
        }
        public DemandeJeton()
        {
        }
    }
}


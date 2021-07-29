using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace winform_TpTransversal.Model
{
    public class Match
    {
        public int id { get; set; }
        public string cotev1 { get; set; }
        public string cotex { get; set; }
        public string resultat { get; set; }
        public string lieumatch { get; set; }
        public string datematch { get; set; }
        public string cotev2 { get; set; }
        public Equipe equipe1 { get; set; }
        public Equipe equipe2 { get; set; }
        public string fini { get; set; }

        public Match()
        {
        }

        public Match(int id)
        {
            this.id = id;
        }

        public Match(int id, string cotev1, string cotex, string resultat, string lieumatch, string datematch, string cotev2, Equipe equipe1, Equipe equipe2, string fini)
        {
            this.id = id;
            this.cotev1 = cotev1;
            this.cotex = cotex;
            this.resultat = resultat;
            this.lieumatch = lieumatch;
            this.datematch = datematch;
            this.cotev2 = cotev2;
            this.equipe1 = equipe1;
            this.equipe2 = equipe2;
            this.fini = fini;
        }
    }                         
}

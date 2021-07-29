using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace winform_TpTransversal.Model
{
    public class Pari
    {
        public string idclient{ get; set; }
        public DetailPari[] detailsparis { get; set; }
        public float mise { get; set; }
        public float coteJouer { get; set; }
        public float gainObtenu { get; set; }


        public Pari(string idclient, DetailPari[] detailsParis, float mise)
        {
            this.idclient = idclient;
            this.detailsparis = detailsParis;
            this.mise = mise;
        }
        public Pari(string idclient, DetailPari[] detailsParis, float mise,float cote)
        {
            this.idclient = idclient;
            this.detailsparis = detailsParis;
            this.mise = mise;
            this.coteJouer = cote;
            this.gainObtenu = cote*mise;
        }
        public Pari()
        {
        }
    }
}

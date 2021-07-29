using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace winform_TpTransversal.Model
{
    public class HistoriquePari
    {
        public int id{ get; set; }
        public int nbperdu { get; set; }
        public List<Model.DetailPari> detailsparis { get; set; }
        public string nbmatch { get; set; }
        public string vcode { get; set; }
        public string dateparis { get; set; }
        public float coteglobal { get; set; }
        public string idclient { get; set; }
        public float gainpossible { get; set; }
        public string gain { get; set; }
        public float nbgain { get; set; }
        public float mise { get; set; }



        public HistoriquePari(int id, int nbperdu, List<Model.DetailPari> detailsparis, string nbmatch, string vcode, string dateparis, float coteglobal, string idclient, float gainpossible, string gain, float nbgain, float mise)
        {
            this.id = id;
            this.nbperdu = nbperdu;
            this.detailsparis = detailsparis;
            this.nbmatch = nbmatch;
            this.vcode = vcode;
            this.dateparis = dateparis;
            this.coteglobal = coteglobal;
            this.idclient = idclient;
            this.gainpossible = gainpossible;
            this.gain = gain;
            this.nbgain = nbgain;
            this.mise = mise;
        }

        public HistoriquePari()
        {
        }
    }
}

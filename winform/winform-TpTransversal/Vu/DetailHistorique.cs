using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using winform_TpTransversal.Model;
using winform_TpTransversal.Service;

namespace winform_TpTransversal.Vu
{
    public partial class DetailHistorique : Form
    {
        string idHistorique;
        ServiceGrails serviceGrails;
        HistoriquePari histPari;
        public DetailHistorique(string idHistorique)
        {
            this.idHistorique = idHistorique;
            InitializeComponent();
        }

        private async void DetailHistorique_Load(object sender, EventArgs e)
        {
            serviceGrails = new ServiceGrails();
            this.histPari = await serviceGrails.GetHistoriqueById(this.idHistorique);
            this.miseLabel.Text = ""+this.histPari.mise;
            this.coteGlobalLabel.Text = "" + this.histPari.coteglobal;
            this.gainObtenu.Text= "" + this.histPari.gainpossible;
            this.generateDetail(this.histPari);
        }
        private void generateDetail(HistoriquePari pari)
        {

            int hauteur = 23,
                margin = 20;
            for (int i = 0; i < pari.detailsparis.Count; i++)
            {
                Label designation = new Label();
                Label prono = new Label();
                Label cote = new Label();
                Label status = new Label();

                //designation
                designation.AutoSize = true;
                designation.BackColor = System.Drawing.Color.Transparent;
                designation.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                designation.Name = "date" + i.ToString();
                designation.Text = pari.detailsparis[i].match.equipe1.nom+"/"+ pari.detailsparis[i].match.equipe2.nom;
                designation.Top = i * hauteur + margin + 10;
                designation.Left = 3;
                this.listMatch.Controls.Add(designation);
                //prono
                prono.AutoSize = true;
                prono.BackColor = System.Drawing.Color.Transparent;
                prono.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                prono.Name = "date" + i.ToString();
                prono.Text = pari.detailsparis[i].prono;
                prono.Top = i * hauteur + margin + 10;
                prono.Left = 153;
                this.listMatch.Controls.Add(prono);
                //cote
                cote.AutoSize = true;
                cote.BackColor = System.Drawing.Color.Transparent;
                cote.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                cote.Name = "date" + i.ToString();
                cote.Text = ""+pari.detailsparis[i].cote;
                cote.Top = i * hauteur + margin + 10;
                cote.Left = 302;
                this.listMatch.Controls.Add(cote);
                //status
                status.AutoSize = true;
                status.BackColor = System.Drawing.Color.Transparent;
                status.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                status.Name = "date" + i.ToString();
                string fin = "en attente";
                if (pari.detailsparis[i].match.fini == "1")
                {
                    fin = "terminer";
                }
                status.Text = fin;
                status.Top = i * hauteur + margin + 10;
                status.Left = 440;
                this.listMatch.Controls.Add(status);
            }
        }
    }
}

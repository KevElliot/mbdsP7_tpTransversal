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
    public partial class Historique : Form
    {
        Model.Login user;
        List<Model.Pari> allPari;
        ServiceGrails serviceGrails;
        List<Model.HistoriquePari> allHist = new List<Model.HistoriquePari>();
        public Historique(Model.Login user, List<Model.Pari> allPari)
        {
            this.user = user;
            this.allPari = allPari;
            InitializeComponent();
            
        }

        private async void Historique_Load(object sender, EventArgs e)
        {
            serviceGrails = new ServiceGrails();
            var res = await serviceGrails.GetHistorique(this.user._id);
            if (res.historique.Count != 0)
            {
                this.panelChargement.Visible = false;
            }
            //Console.WriteLine("res: " + res.historique.Count);
            for (int i = 0; i < res.historique.Count; i++)
            {
                this.allHist.Add(res.historique[i]);
            }
            //Console.WriteLine("total hist: "+this.allHist.Count);
            this.generateHistorique(this.allHist);
        }

        private void homeBtn_Click(object sender, EventArgs e)
        {
            Hide();
            Home h = new Home(this.user,this.allPari);
            h.Show();
        }

        private void profilBtn_Click(object sender, EventArgs e)
        {
            Profil profil = new Profil(this.user, this.allPari);
            profil.Show();
        }

        private void demandeJetonBtn_Click(object sender, EventArgs e)
        {
            DemandeJeton demandeJeton = new DemandeJeton(this.user, this.allPari);
            demandeJeton.Show();
        }

        private void logOutbtn_Click(object sender, EventArgs e)
        {
            DialogResult dialogResult = MessageBox.Show("Etes vous sur de vouloir vous deconnecter?", "Déconnection", MessageBoxButtons.YesNo);
            if (dialogResult == DialogResult.Yes)
            {
                Dispose();
                this.user = new Model.Login();
                this.allPari = new List<Model.Pari>();
                Home h = new Home(user, allPari);
                h.Show();
            }
            else if (dialogResult == DialogResult.No)
            {
                this.Close();
                Home h = new Home(this.user, allPari);
                h.Show();
            }
        }

        private void generateHistorique(List<Model.HistoriquePari> totalHist)
        {
            int hauteur = 23,
                largeur = 75,
                margin = 20;

            for (int i = 0; i < totalHist.Count; i++)
            {

                Label datePari = new Label();
                Label cote = new Label();
                Label mise = new Label();
                Label gain = new Label();
                Label statut = new Label();
                Button detail = new Button();

                //date Pari
                datePari.AutoSize = true;
                datePari.BackColor = System.Drawing.Color.Transparent;
                datePari.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                datePari.Name = "date" + i.ToString();
                datePari.Text = totalHist[i].dateparis;
                datePari.Top = i * hauteur + margin + 10;
                datePari.Left = 50;
                this.containerHist.Controls.Add(datePari);

                //cote
                cote.AutoSize = true;
                cote.BackColor = System.Drawing.Color.Transparent;
                cote.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                cote.Name = "cote" + i.ToString();
                cote.Text = ""+ totalHist[i].coteglobal;
                cote.Top = i * hauteur + margin + 10;
                cote.Left = 196;
                this.containerHist.Controls.Add(cote);

                //mise
                mise.AutoSize = true;
                mise.BackColor = System.Drawing.Color.Transparent;
                mise.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                mise.Name = "mise" + i.ToString();
                //Console.WriteLine(JsonConvert.SerializeObject(totalHist[i]));
                mise.Text = ""+ totalHist[i].mise;
                mise.Top = i * hauteur + margin + 10;
                mise.Left = 343;
                this.containerHist.Controls.Add(mise);

                //gain
                gain.AutoSize = true;
                gain.BackColor = System.Drawing.Color.Transparent;
                gain.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                gain.Name = "mise" + i.ToString();
                gain.Text = ""+totalHist[i].gainpossible;
                gain.Top = i * hauteur + margin + 10;
                gain.Left = 499;
                this.containerHist.Controls.Add(gain);

                //statut
                statut.AutoSize = true;
                statut.BackColor = System.Drawing.Color.Transparent;
                statut.Font = new System.Drawing.Font("Segoe UI", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
                statut.Name = "mise" + i.ToString();
                string stat = "En cours";
                if (totalHist[i].gain=="KO")
                {
                    stat = "Defaite";
                }
                if (totalHist[i].gain == "OK")
                {
                    stat = "Victoire";
                }
                statut.Text = stat;
                statut.Top = i * hauteur + margin + 10;
                statut.Left = 650;
                this.containerHist.Controls.Add(statut);

                //detail
                detail.Width = largeur;
                detail.Height = hauteur;
                detail.Name = "detail" + i.ToString();
                detail.Text = "detail";
                detail.Tag = allHist[i];
                detail.BackColor = Color.ForestGreen;
                detail.FlatStyle = FlatStyle.Popup;
                // ajouter un listener
                detail.Click += detail_Click;
                detail.Top = i * hauteur + margin + 10;
                detail.Left = 785;
                this.containerHist.Controls.Add(detail);
                margin += 8;
            }
        }
        private void detail_Click(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            HistoriquePari h = btn.Tag as HistoriquePari;
            string id = ""+h.id;
            Vu.DetailHistorique detailHist = new DetailHistorique(id);
            detailHist.Show();
        }
    }
}

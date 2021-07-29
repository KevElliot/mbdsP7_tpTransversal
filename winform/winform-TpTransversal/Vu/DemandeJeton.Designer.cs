
namespace winform_TpTransversal.Vu
{
    partial class DemandeJeton
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label6 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.jetonTextbox = new System.Windows.Forms.TextBox();
            this.validerBtn = new System.Windows.Forms.Button();
            this.groupDemande = new System.Windows.Forms.GroupBox();
            this.groupDemande.SuspendLayout();
            this.SuspendLayout();
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(2, 43);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(317, 13);
            this.label6.TabIndex = 16;
            this.label6.Text = "La demande de jetons doit etre supérieur à 10 et inférieur à 1000. ";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(2, 19);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(154, 18);
            this.label1.TabIndex = 17;
            this.label1.Text = "Demande de Jeton:\r\n";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(2, 75);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(118, 13);
            this.label2.TabIndex = 18;
            this.label2.Text = "Jetons à demander:";
            // 
            // jetonTextbox
            // 
            this.jetonTextbox.BackColor = System.Drawing.SystemColors.InactiveBorder;
            this.jetonTextbox.Location = new System.Drawing.Point(126, 72);
            this.jetonTextbox.Name = "jetonTextbox";
            this.jetonTextbox.Size = new System.Drawing.Size(158, 20);
            this.jetonTextbox.TabIndex = 19;
            // 
            // validerBtn
            // 
            this.validerBtn.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.validerBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.validerBtn.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.validerBtn.Font = new System.Drawing.Font("Rockwell", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.validerBtn.Location = new System.Drawing.Point(126, 109);
            this.validerBtn.Name = "validerBtn";
            this.validerBtn.Size = new System.Drawing.Size(158, 33);
            this.validerBtn.TabIndex = 20;
            this.validerBtn.Text = "Valider";
            this.validerBtn.UseVisualStyleBackColor = false;
            this.validerBtn.Click += new System.EventHandler(this.validerBtn_Click);
            // 
            // groupDemande
            // 
            this.groupDemande.BackColor = System.Drawing.Color.Transparent;
            this.groupDemande.Controls.Add(this.validerBtn);
            this.groupDemande.Controls.Add(this.jetonTextbox);
            this.groupDemande.Controls.Add(this.label2);
            this.groupDemande.Controls.Add(this.label1);
            this.groupDemande.Controls.Add(this.label6);
            this.groupDemande.Location = new System.Drawing.Point(10, 32);
            this.groupDemande.Name = "groupDemande";
            this.groupDemande.Size = new System.Drawing.Size(334, 177);
            this.groupDemande.TabIndex = 21;
            this.groupDemande.TabStop = false;
            this.groupDemande.Text = "Demande de jeton";
            // 
            // DemandeJeton
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(355, 226);
            this.Controls.Add(this.groupDemande);
            this.MaximizeBox = false;
            this.Name = "DemandeJeton";
            this.RightToLeftLayout = true;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Demande de jeton";
            this.groupDemande.ResumeLayout(false);
            this.groupDemande.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        public System.Windows.Forms.TextBox jetonTextbox;
        private System.Windows.Forms.Button validerBtn;
        private System.Windows.Forms.GroupBox groupDemande;
    }
}
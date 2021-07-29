
namespace winform_TpTransversal.Vu
{
    partial class Miser
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
            this.validerMise = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.mise = new System.Windows.Forms.TextBox();
            this.groupDemande = new System.Windows.Forms.GroupBox();
            this.annulerBtn = new System.Windows.Forms.Button();
            this.groupDemande.SuspendLayout();
            this.SuspendLayout();
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(6, 51);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(41, 13);
            this.label6.TabIndex = 16;
            this.label6.Text = "Jetons:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 11.25F, ((System.Drawing.FontStyle)((System.Drawing.FontStyle.Bold | System.Drawing.FontStyle.Underline))), System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(6, 24);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(210, 18);
            this.label1.TabIndex = 17;
            this.label1.Text = "Faite une mise pour parier:\r\n";
            // 
            // validerMise
            // 
            this.validerMise.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.validerMise.Cursor = System.Windows.Forms.Cursors.Hand;
            this.validerMise.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.validerMise.Font = new System.Drawing.Font("Rockwell", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.validerMise.Location = new System.Drawing.Point(168, 99);
            this.validerMise.Name = "validerMise";
            this.validerMise.Size = new System.Drawing.Size(158, 33);
            this.validerMise.TabIndex = 20;
            this.validerMise.Text = "Valider";
            this.validerMise.UseVisualStyleBackColor = false;
            this.validerMise.Click += new System.EventHandler(this.validerMise_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 6.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(7, 84);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(175, 12);
            this.label4.TabIndex = 23;
            this.label4.Text = "Votre mise doit etre inferieur à vos jetons.";
            // 
            // mise
            // 
            this.mise.AccessibleName = "mise";
            this.mise.BackColor = System.Drawing.SystemColors.ControlLight;
            this.mise.Location = new System.Drawing.Point(48, 48);
            this.mise.Name = "mise";
            this.mise.Size = new System.Drawing.Size(168, 20);
            this.mise.TabIndex = 24;
            // 
            // groupDemande
            // 
            this.groupDemande.BackColor = System.Drawing.Color.Transparent;
            this.groupDemande.Controls.Add(this.annulerBtn);
            this.groupDemande.Controls.Add(this.mise);
            this.groupDemande.Controls.Add(this.label4);
            this.groupDemande.Controls.Add(this.validerMise);
            this.groupDemande.Controls.Add(this.label1);
            this.groupDemande.Controls.Add(this.label6);
            this.groupDemande.Location = new System.Drawing.Point(12, 12);
            this.groupDemande.Name = "groupDemande";
            this.groupDemande.Size = new System.Drawing.Size(334, 146);
            this.groupDemande.TabIndex = 23;
            this.groupDemande.TabStop = false;
            this.groupDemande.Text = "Parier:";
            // 
            // annulerBtn
            // 
            this.annulerBtn.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(192)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.annulerBtn.Cursor = System.Windows.Forms.Cursors.Hand;
            this.annulerBtn.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.annulerBtn.Font = new System.Drawing.Font("Rockwell", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.annulerBtn.Location = new System.Drawing.Point(0, 99);
            this.annulerBtn.Name = "annulerBtn";
            this.annulerBtn.Size = new System.Drawing.Size(158, 33);
            this.annulerBtn.TabIndex = 25;
            this.annulerBtn.Text = "Annuler";
            this.annulerBtn.UseVisualStyleBackColor = false;
            this.annulerBtn.Click += new System.EventHandler(this.annulerBtn_Click);
            // 
            // Miser
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(350, 163);
            this.Controls.Add(this.groupDemande);
            this.MaximizeBox = false;
            this.Name = "Miser";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Miser";
            this.groupDemande.ResumeLayout(false);
            this.groupDemande.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button validerMise;
        private System.Windows.Forms.Label label4;
        public System.Windows.Forms.TextBox mise;
        private System.Windows.Forms.GroupBox groupDemande;
        private System.Windows.Forms.Button annulerBtn;
    }
}
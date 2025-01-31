package embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class EmbedCreator extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if (event.getName().equals("createembed")) {

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Create Embed");
            embed.setDescription("Create a embed by clicking on the button.");
            embed.setColor(0x1ABC9C);

            event.replyEmbeds(embed.build())
                    .addActionRow((Button.primary("create-embed", "Create a Embed")))
                    .queue();

        }
    }


    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("create-embed")) {
            Modal modal = Modal.create("embedmodal", "Create Embed!")
                    .addActionRows(
                            ActionRow.of(TextInput.create("embed-title", "Embed Title", TextInputStyle.SHORT)
                                    .setRequired(true)
                                    .setPlaceholder("Enter the title of the embed")
                                    .build()),
                            ActionRow.of(TextInput.create("embed-description", "Embed Description", TextInputStyle.PARAGRAPH)
                                    .setRequired(true)
                                    .setPlaceholder("Enter the description of the embed")
                                    .build()),
                            ActionRow.of(TextInput.create("embed-color", "Embed Color", TextInputStyle.SHORT)
                                    .setRequired(false)
                                    .setPlaceholder("Enter a hex color code (e.g., #1ABC9C)")
                                    .build())
                    ).build();
            event.replyModal(modal).queue();

            event.getHook().retrieveOriginal().queue(interaction -> {
                ModalInteractionEvent modalEvent = (ModalInteractionEvent) interaction;

                String title = modalEvent.getValue("embed-title").getAsString();
                String description = modalEvent.getValue("embed-description").getAsString();
                String color = modalEvent.getValue("embed-color") != null
                        ? modalEvent.getValue("embed-color").getAsString()
                        : null;

                EmbedBuilder embedfinal = new EmbedBuilder();
                embedfinal.setTitle(title);
                embedfinal.setDescription(description);

                if (color != null && color.matches("^#[0-9A-Fa-f]{6}$")) {
                    embedfinal.setColor(Color.decode(color));
                } else {
                    embedfinal.setColor(0x1ABC9C);
                    embedfinal.setTitle("Could not create embed!");
                    embedfinal.setDescription("Please check if you made any mistakes while creating the embed!");
                }
            });


        }
    }




}


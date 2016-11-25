class ToolRoom < Place
  def visit_by(player)
    :wait_for_buy_tool
  end

  def act_to(player, response)
    if (response == :exit)
      :end_turn
    elsif (response == :buy_block)
      player.buy_tool(Block.new)
    elsif (response == :buy_bomb)
      player.buy_tool(Bomb.new)
    elsif (response == :buy_robot)
      player.buy_tool(Robot.new)
    end
    :end_turn
  end

end

class ToolRoom < Place
  def visit_by(player)
    :wait_for_buy_tool
  end
end

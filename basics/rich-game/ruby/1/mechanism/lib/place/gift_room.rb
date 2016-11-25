class GiftRoom < Place
  def visit_by(player)
    :wait_for_choose_gift
  end
end
